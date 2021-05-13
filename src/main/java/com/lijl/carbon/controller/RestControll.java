package com.lijl.carbon.controller;

import com.lijl.carbon.handling.ConsumerService;
import com.lijl.carbon.pojo.GeneralData;
import com.lijl.carbon.pojo.WorkEntity;
import com.lijl.carbon.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lijl
 * @ClassName RestControll
 * @Description 控制层
 * @Date 2020/8/13 13:43
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/restContro")
public class RestControll {


    private ConsumerService consumerService;

    @Autowired
    public void setConsumerService(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    private final String versionStr = "1.0";

    @PostMapping(value = "/common/{workType}/save",produces={"application/json;charset=UTF-8"})
    public Map<String,Object> saveCommon(@PathVariable String workType, @RequestBody WorkEntity workEntity) {
        Map<String,Object> retMap = new HashMap<>(2);
        retMap.put("code",1003);
        retMap.put("msg","失败");
        if (workEntity!=null){
            String version = workEntity.getVersion();
            String param = workEntity.getParam();
            if (version!=null && !"".equals(version) && versionStr.equals(version)){
                if (param!=null && !"".equals(param) && workType!=null && !"".equals(workType)){
                    consumerService.saveCommon(param,workType);
                    retMap.put("code",0);
                    retMap.put("msg","成功");
                }else{
                    retMap.put("code",1002);
                    retMap.put("msg","参数错误");
                }
            }else{
                retMap.put("code",1001);
                retMap.put("msg","版本号错误");
            }
        }
        return retMap;
    }


    /**
     * @Author lijiale
     * @MethodName saveCommonData
     * @Description 通用数据处理接口
     * @Date 9:12 2021/5/13
     * @Version 1.0
     * @param generalData
     * @return: com.lijl.carbon.utils.RespBean
    **/
    @RequestMapping(value = "/saveGeneralData",produces={"application/json;charset=UTF-8"})
    public RespBean saveGeneralData(@RequestBody GeneralData generalData){
        if (generalData!=null){
            boolean ret = consumerService.saveGeneralData(generalData);
            if (ret){
                return RespBean.ok("成功");
            }
        }
        return RespBean.error("失败");
    }
}

