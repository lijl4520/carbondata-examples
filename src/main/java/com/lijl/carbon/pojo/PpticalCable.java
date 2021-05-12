package com.lijl.carbon.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Lijl
 * @ClassName PpticalCable
 * @Description TODO
 * @Date 2021/4/26 10:36
 * @Version 1.0
 */
@Data
public class PpticalCable implements Serializable {
    //光缆段ID
    private Integer opticalid;
    //测试纤芯数
    private Integer testfibrenum;
    //达标纤芯数
    private Integer qualifiedfibrenum;
    //测试最长长度（km）
    private Double testmaxlength;
    //测试中位长度（km）
    private Double testmedianlength;
    //测试光缆段评分
    private Double testopticalscore;
    //测试数据评分
    private Double testdatascore;
    //平均衰耗
    private Double averagedecay;
    //测试上传时间
    private String lastuploadtime;
    //报告URL地址
    private String reporturl;
    //多条纤芯测试数据
    private List<FibreinfoData> fibreinfos;
}
