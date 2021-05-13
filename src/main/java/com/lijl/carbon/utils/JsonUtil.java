package com.lijl.carbon.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @Author Lijl
 * @ClassName JsonUtil
 * @Description 生成json文件工具类
 * @Date 2020/8/31 10:04
 * @Version 1.0
 */
@Slf4j
public class JsonUtil {


    /**
     * @Author Lijl
     * @MethodName createJSONFile
     * @Description TODO
     * @Date 11:07 2020/8/31
     * @Version 1.0
     * @param jsonString
     * @param fullPath
     * @return: boolean
     */
    public static boolean createJSONFile(String jsonString,String fullPath){
        boolean flag = true;
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if(!file.exists()) {
                file.createNewFile();
            }
            file.createNewFile();//创建新文件
            if(jsonString.indexOf("'")!=-1){
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("'", "\\'");
            }
            if(jsonString.indexOf("\"")!=-1){
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("\"", "\\\"");
            }
            if(jsonString.indexOf("\r\n")!=-1){
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
            }
            if(jsonString.indexOf("\n")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                jsonString = jsonString.replaceAll("\n", "\\u000a");
            }
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            log.error("创建文件失败,失败原因:{}",e.getMessage());
        }
        return flag;
    }
}

