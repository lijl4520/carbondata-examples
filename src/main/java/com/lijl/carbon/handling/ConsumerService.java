package com.lijl.carbon.handling;

import com.lijl.carbon.pojo.GeneralData;
import com.lijl.carbon.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @Author Lijl
 * @ClassName ConsumerService
 * @Description 消费处理类
 * @Date 2020/8/31 10:04
 * @Version 1.0
 */
@Service
@Slf4j
public class ConsumerService {


    @Async("asyncServiceExecutor")
    public void saveCommon(String param,String workType) {
        try {
            String dateStr = getDateStr();
            // 拼接文件完整路径// 生成json格式文件
            String finleName = dateStr + "_"+ workType +".json";
            String fullPath = "/opt/script/dat/workorder/"+finleName;
            boolean ret = JsonUtil.createJSONFile(param, fullPath);
            if (!ret){
                log.error("写入文件失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("消费异常,异常原因:{}",e.getMessage());
        }
    }


    /**
     * @Author Lijl
     * @MethodName getDateStr
     * @Description 获取时间年月日时分秒毫秒
     * @Date 11:11 2020/9/14
     * @Version 1.0
     * @param
     * @return: java.lang.String
    **/
    private String getDateStr() {
        String dataStr = "";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter.ofPattern("yyyyMMddHHmmsszzz");
        String[] dataTimeArr = dateTime.toString().split("T");
        String[] data = dataTimeArr[0].split("-");
        dataStr += data[0]+data[1]+data[2];
        String[] timeArr = dataTimeArr[1].split(":");
        dataStr += timeArr[0]+timeArr[1]+timeArr[2].split("\\.")[0]+timeArr[2].split("\\.")[1];
        return dataStr;
    }

    /**
     * @Author lijiale
     * @MethodName saveGeneralData
     * @Description 生成通用数据文件
     * @Date 10:34 2021/5/13
     * @Version 1.0
     * @param generalData
     * @return: boolean
    **/
    public boolean saveGeneralData(GeneralData generalData) {
        try {
            String dateStr = getDateStr();
            // 拼接文件完整路径// 生成json格式文件
            String finleName = dateStr + "_"+ generalData.getCommonType() +".json";
            String fullPath = "/opt/script/dat/generalFiles/"+finleName;
            return JsonUtil.createJSONFile(generalData.getParam(), fullPath);
        }catch (Exception e){
            e.printStackTrace();
            log.error("消费异常,异常原因:{}",e.getMessage());
        }
        return false;
    }
}
