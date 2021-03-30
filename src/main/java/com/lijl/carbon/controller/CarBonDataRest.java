package com.lijl.carbon.controller;

import com.lijl.carbon.hadoop.utils.SqlUtils;
import com.lijl.carbon.utils.RespBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Lijl
 * @ClassName RestController
 * @Description TODO
 * @Date 2021/3/30 13:59
 * @Version 1.0
 */
@RestController
public class CarBonDataRest {

    @PostMapping(value = "/getListBySqlStr")
    public RespBean getListBySqlStr(String sqlStr){
        List list = SqlUtils.queryList(sqlStr);
        if (list!=null){
            return RespBean.ok("成功",list);
        }
        return RespBean.error("失败");
    }
}
