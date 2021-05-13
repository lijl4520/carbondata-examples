package com.lijl.carbon.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Lijl
 * @ClassName FibreinfoData
 * @Description TODO
 * @Date 2021/4/26 10:39
 * @Version 1.0
 */
@Data
public class FibreinfoData implements Serializable {
    //光缆段ID
    private String opticalid;
    //纤芯号
    private String testfibrenum;
    //结尾位置
    private String endlength;
    //分析范围内平均衰耗
    private String concernrangeaverageloss;
    //分析范围内总衰耗
    private String concernrangetotalloss;
    //测试全程平均衰耗
    private String avgloss;
    //测试全程总衰耗
    private String totalloss;
    //是否中断
    private Boolean isborken;
}
