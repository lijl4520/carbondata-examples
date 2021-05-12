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
    private Long testfibrenum;
    //结尾位置
    private Double endlength;
    //分析范围内平均衰耗
    private Double concernrangeaverageloss;
    //分析范围内总衰耗
    private Double concernrangetotalloss;
    //测试全程平均衰耗
    private Double avgloss;
    //测试全程总衰耗
    private Double totalloss;
    //是否中断
    private Boolean isborken;
}
