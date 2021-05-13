package com.lijl.carbon.controller;

import com.lijl.carbon.handling.DataHandling;
import com.lijl.carbon.pojo.PpticalCable;
import com.lijl.carbon.utils.ReqBean;
import com.lijl.carbon.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Author Lijl
 * @ClassName RestController
 * @Description
 * @Date 2021/3/30 13:59
 * @Version 1.0
 */
@RestController
public class CarBonDataRest {


    private DataHandling dataHandling;

    @Autowired
    public void setDataHandling(DataHandling dataHandling){
        this.dataHandling = dataHandling;
    }

    /**
     * @Author lijiale
     * @MethodName getListBySqlStr
     * @Description 接收请求
     * @Date 14:40 2021/4/12
     * @Version 1.0
     * @param reqBean
     * @return: com.lijl.carbon.utils.RespBean
    **/
    @PostMapping(value = "/getListBySqlStr",produces = "application/json;charset=UTF-8")
    public RespBean getListBySqlStr(@RequestBody ReqBean reqBean){
        Map retMap = dataHandling.handlingData(reqBean);
        if (retMap!=null){
            return RespBean.ok("成功",retMap);
        }
        return RespBean.error("失败");
    }

    /**
     * @Author lijiale
     * @MethodName inset
     * @Description 新增
     * @Date 17:06 2021/5/8
     * @Version 1.0
     * @param ppticalCable
     * @return: com.lijl.carbon.utils.RespBean
    **/
    @PostMapping(value = "/fiberDoctor",produces = "application/json;charset=UTF-8")
    public RespBean inset(@RequestBody PpticalCable ppticalCable){
        try {
            if (ppticalCable!=null){
                boolean bol = dataHandling.insert(ppticalCable);
                if (bol){
                    return RespBean.ok("成功");
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return RespBean.error("失败");
    }


}
