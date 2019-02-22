package com.freedom.admin.service;

import com.freedom.admin.model.SysFile;
import com.freedom.admin.mapper.SysFileMapper;
import com.freedom.core.pojo.BaseService;
import com.freedom.core.pojo.PageUtils;
import com.freedom.core.utils.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
* 文件图片头像记录
* @author Bai
* @date 2019/02/22 11:26
*/
@Service
public class SysFileService extends BaseService{

    @Autowired
    private SysFileMapper sysFileMapper;

    /*
    * 分页查询
    */
    public PageUtils queryAll(SysFile sysFile, int pageNum, int pageSize, String orderBy) throws Exception{
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<SysFile> info = new PageInfo<>(sysFileMapper.findByListDynamic(sysFile));
        return new PageUtils(info);
    }
    /*
    *主键查询
    */
    public SysFile queryById(Long id) throws Exception {
        return sysFileMapper.findById(id);
    }
    /*
    *新增
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void add(SysFile sysFile) throws Exception{
        sysFile.setId(IdWorker.getInstance().nextId());
        sysFileMapper.insertDynamic(sysFile);
    }
    /*
    *修改
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void update(SysFile sysFile) throws Exception {
        sysFileMapper.updateDynamic(sysFile);
    }
    /*
    *删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) throws Exception {
        sysFileMapper.remove(id);
    }
    /*
    * 批量删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(Long[] ids)throws Exception {
        sysFileMapper.batchRemove(ids);
    }
    /*
    sha1查询是否存在
     */
    public SysFile isFile(String fileSHA1) {
        return sysFileMapper.isFile(fileSHA1);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void addList(List<SysFile> files){
        files.stream().forEach(o->{
            o.setId(IdWorker.getInstance().nextId());
            o.setCreateTime(new Timestamp(System.currentTimeMillis()));
            o.setCreateUser(new Long(1L));
            o.setIsDelete(true);
            o.setStatus(true);
        });
        sysFileMapper.insertListModel(files);
    }
}