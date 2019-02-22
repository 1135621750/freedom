package com.freedom.admin.utils;

import com.freedom.admin.model.SysFile;
import com.freedom.core.config.MyYml;
import com.freedom.core.exceptions.BusiException;
import com.freedom.core.result.R;
import com.freedom.core.utils.SpringContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUpload {

    /**
     * 创建一个Upload实体对象
     * @param multipartFile MultipartFile对象
     * @param modulePath 文件模块路径
     */
    public static SysFile getFile(MultipartFile multipartFile, String modulePath){
        if (multipartFile.getSize() == 0){
            throw new BusiException(R.NO_FILE_NULL);
        }
        SysFile sysFile = new SysFile();
        sysFile.setMime(multipartFile.getContentType());
        sysFile.setSize(multipartFile.getSize());
        sysFile.setName(FileUpload.genFileName(multipartFile.getOriginalFilename()));
        sysFile.setPath(getUploadPath() + modulePath + FileUpload.genDateMkdir("yyyyMMdd") + sysFile.getName());
        return sysFile;
    }

    /**
     * 判断文件是否为支持的格式
     * @param multipartFile MultipartFile对象
     * @param types 支持的文件类型数组
     */
    public static boolean isContentType(MultipartFile multipartFile, String[] types){
        List<String> typeList = Arrays.asList(types);
        return typeList.contains(multipartFile.getContentType());
    }

    /**
     * 获取文件上传保存路径
     */
    public static String getUploadPath(){
        MyYml properties = SpringContextHolder.getBean(MyYml.class);
        return properties.getFileUploadPath().replace("/", "");
    }

    /**
     * 生成随机且唯一的文件名
     * @param originalFilename 文件路径或文件名称
     */
    public static String genFileName(String originalFilename){
        String fileSuffix = getFileSuffix(originalFilename);
        return UUID.randomUUID().toString().replace("-", "") + fileSuffix;
    }

    /**
     * 文件后缀
     * @param fileName 文件名
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if(!fileName.isEmpty()){
            return fileName.substring(fileName.lastIndexOf("."),fileName.length());
        }
        return "";
    }

    /**
     * 生成指定格式的目录名称(日期格式前后增加"/")
     * @param format 日期格式
     */
    public static String genDateMkdir(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return "/" + sdf.format(new Date()) + "/";
    }

    /**
     * 获取目标文件对象
     * @param sysFile 上传实体类
     */
    public static File getDestFile(SysFile sysFile) throws IOException {

        // 创建保存文件对象
        String path = sysFile.getPath().replace(getUploadPath(), "");
        String filePath = getUploadPath() + path;
        File dest = new File(filePath.replace("//", "/"));
        if(!dest.exists()){
            dest.getParentFile().mkdirs();
            dest.createNewFile();
        }

        return dest;
    }

    /**
     * 保存文件及获取文件MD5值和SHA1值
     * @param multipartFile MultipartFile对象
     * @param sysFile Upload
     */
    public static void transferTo(MultipartFile multipartFile, SysFile sysFile) throws IOException, NoSuchAlgorithmException {

        byte[] buffer = new byte[4096];
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        try (OutputStream fos = Files.newOutputStream(getDestFile(sysFile).toPath()); InputStream fis = multipartFile.getInputStream()) {
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                md5.update(buffer, 0, len);
                sha1.update(buffer, 0, len);
            }
            fos.flush();
        }
        BigInteger MD5Bi = new BigInteger(1, md5.digest());
        BigInteger SHA1Bi = new BigInteger(1, sha1.digest());
        sysFile.setMd5(MD5Bi.toString(16));
        sysFile.setSha1(SHA1Bi.toString(16));
    }

    /**
     * 获取文件的SHA1值
     * @param multipartFile multipartFile对象
     */
    public static String getFileSHA1(MultipartFile multipartFile) {
        if (multipartFile.getSize() == 0){
            throw new BusiException(R.NO_FILE_NULL);
        }
        byte[] buffer = new byte[4096];
        try (InputStream fis = multipartFile.getInputStream()) {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                sha1.update(buffer, 0, len);
            }
            BigInteger SHA1Bi = new BigInteger(1, sha1.digest());
            return SHA1Bi.toString(16);
        } catch (IOException | NoSuchAlgorithmException e) {
            return null;
        }
    }
}
