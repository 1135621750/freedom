package com.freedom.admin.web;

import cn.hutool.core.lang.Assert;
import com.freedom.admin.model.SysFile;
import com.freedom.admin.service.SysFileService;
import com.freedom.admin.utils.FileUpload;
import com.freedom.core.exceptions.BusiException;
import com.freedom.core.result.JsonResult;
import com.freedom.core.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Api(value = "上传获取")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private SysFileService sysFileService;

    @ApiOperation(value = "上传图片",notes = "只能上传gif、jpg、jpeg、png后缀文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "上传文件",required = true,dataType = "MultipartFile",allowMultiple = true)
    })
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public JsonResult<?> image(@RequestParam("file") MultipartFile file)throws Exception {

        // 创建Upload实体对象
        SysFile sysFile = FileUpload.getFile(file, "/images");
        try {
            return saveImage(file, sysFile);
        } catch (IOException | NoSuchAlgorithmException e) {
            return JsonResult.error("上传图片失败");
        }
    }

    @ApiOperation(value = "上传头像",notes = "未做图片处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "上传文件",required = true,dataType = "MultipartFile",allowMultiple = true)
    })
    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public JsonResult<?> picture(@RequestParam("file") MultipartFile file) throws Exception{

        // 创建Upload实体对象
        SysFile sysFile = FileUpload.getFile(file, "/picture");
        try {
            return saveImage(file, sysFile);
        } catch (IOException | NoSuchAlgorithmException e) {
            return JsonResult.error("上传头像失败");
        }
    }


    @ApiOperation(value = "获取文件",notes = "文件包括图片文件等")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "url",value = "文件路径",required = true,dataType = "String")
    })
    @RequestMapping(value = "/findFile",method = RequestMethod.GET)
    public void findFile(String url, HttpServletResponse response) throws Exception {
        Assert.notBlank(url);
        File file = new File(url);
        if (!file.exists()) {
            throw new BusiException(R.FILE_QUERY_FAILED);
        }
        try{
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }catch (Exception e){
            throw new BusiException(R.FILE_DOWNLOAD_FAILED);
        }
    }

    @ApiOperation(value = "下载文件",notes = "文件包括图片文件等")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url",value = "文件路径",required = true)
    })
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void downloadFile(String url, HttpServletResponse response) throws Exception {
        Assert.notBlank(url);
        File file = new File(url);
        if (!file.exists()) {
            throw new BusiException(R.FILE_QUERY_FAILED);
        }
        try{
            //文件转换流
            IOUtils.closeQuietly(new FileInputStream(file));
            byte[] bytes = Files.readAllBytes(file.toPath());
            //设置名称
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename="
                    +"freedom"+FileUpload.getFileSuffix(url));
            response.addHeader("Content-Length", "" + bytes.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            //输出
            IOUtils.write(bytes, response.getOutputStream());
        }catch (Exception e){
            throw new BusiException(R.FILE_DOWNLOAD_FAILED);
        }
    }



    /**
     * 批量上传文件（不做文件格式验证）
     */
    @RequestMapping(value = "/batchFile",method = RequestMethod.POST)
    public JsonResult<?> batchFile(HttpServletRequest request) throws Exception {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (!multipartResolver.isMultipart(request)) {
            return JsonResult.error(R.NO_FILE_TYPE);
        }
        //写文件
        List<SysFile> list = new ArrayList<>();
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multiRequest.getFileNames();
        while (iterator.hasNext()) {
            // 取得上传文件
            MultipartFile multipartFile = multiRequest.getFile(iterator.next());
            // 判断文件是否存在
            SysFile isFile = sysFileService.isFile(FileUpload.getFileSHA1(multipartFile));
            if (isFile != null) {
                return JsonResult.error(R.FILE_EXIST,isFile);
            }
            if (multipartFile != null) {
                SysFile upload = FileUpload.getFile(multipartFile, "/file");
                FileUpload.transferTo(multipartFile, upload);
                list.add(upload);
            }
        }
        // 将文件信息保存到数据库中
        sysFileService.addList(list);
        return JsonResult.success(list);
    }

    /**
     * 保存上传的web格式图片
     */
    private JsonResult<?> saveImage(MultipartFile multipartFile, SysFile sysFile) throws Exception {
        // 判断是否为支持的图片格式
        String[] types = {
                "image/gif",
                "image/jpg",
                "image/jpeg",
                "image/png"
        };
        if(!FileUpload.isContentType(multipartFile, types)){
            throw new BusiException(R.NO_FILE_TYPE);
        }

        // 判断图片是否存在
        SysFile isFile = sysFileService.isFile(FileUpload.getFileSHA1(multipartFile));
        if (isFile != null) {
            return JsonResult.error(R.FILE_EXIST,isFile);
        }

        FileUpload.transferTo(multipartFile, sysFile);
        // 将文件信息保存到数据库中
        sysFileService.add(sysFile);
        return JsonResult.success(sysFile);
    }
}
