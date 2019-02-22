package com.freedom.core.utils;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Objects;

/**
 * 缓存工具类，也可以使用@Autowired注入
 */
@Slf4j
public class EhCacheUtil {

    /**
     * 获取EhCacheManager管理对象
     */
    public static CacheManager getCacheManager(){
        return SpringContextHolder.getBean(CacheManager.class);
    }

    /**
     * 获取EhCache缓存对象
     */
    public static Cache getCache(String name){
        return getCacheManager().getCache(name);
    }

    /**
     * 获取字典缓存对象
     */
    public static Cache getDictCache(){
        return getCacheManager().getCache("dictionary");
    }

    /**
     * 新增缓存
     * @param cache 缓存对象,在xml文件中配置获取的对象
     * @param key 键
     * @param val 值
     */
    public static void put(Cache cache,String key,String val){
        try{
            cache.put(new Element(key,val));
        }catch (Exception e){
            log.error("新增缓存失败",e);
        }
    }

    /**
     * 获取缓存
     * @param cache 缓存对象,在xml文件中配置获取的对象
     * @param key 键
     * @param clazz 需要转换的实体
     * @param <T> 返回转换对应的实体
     * @return
     */
    public static <T> T get(Cache cache ,String key,Class<T> clazz){
        Element element = cache.get(key);
        if(Objects.isNull(element))
            return null;
        return Convert.convert(clazz, element.getObjectValue());
    }
}
