package com.lijl.carbon.utils;

import org.apache.commons.collections.CollectionUtils;
import sun.security.util.Cache;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Lijl
 * @ClassName PageHelper
 * @Description TODO
 * @Date 2021/4/16 9:37
 * @Version 1.0
 */
public class PageHelper {

    private static Cache cache = Cache.newHardMemoryCache(0,600*6);


    public static synchronized void setCacheData(String key , List<Map> list){
        if (cache.get(key)!=null){
            cache.remove(key);
        }
        cache.put(key,list);
    }

    public static List<Map> getCacheData(String key){
        Object o = cache.get(key);
        if (o!=null){
            return (List<Map>) o;
        }
        return null;
    }

    /**
     * @Author lijiale
     * @MethodName pagination
     * @Description 内存分页
     * @Date 14:01 2021/4/25
     * @Version 1.0
     * @param records 待分页数据
     * @param pageNum 当前页码
     * @param pageSize 每页显示的条数
     * @return: java.util.List<T>
    **/
    public static <T> List<T> pagination(List<T> records, int pageNum, int pageSize) {
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        if (pageNum < 0 || pageSize < 0) {
            return Collections.emptyList();
        }
        records = records.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return records;
    }

}
