package com.lijl.carbon.handling;

import com.lijl.carbon.hadoop.utils.SqlUtils;
import com.lijl.carbon.pojo.FibreinfoData;
import com.lijl.carbon.pojo.PpticalCable;
import com.lijl.carbon.utils.PageHelper;
import com.lijl.carbon.utils.ReqBean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Lijl
 * @ClassName DataHandling
 * @Description TODO
 * @Date 2021/4/16 10:27
 * @Version 1.0
 */
@Service
public class DataHandling {

    public Map handlingData(ReqBean reqBean){
        String sqlStr = reqBean.getSqlStr();
        List<Map> cacheData = PageHelper.getCacheData(sqlStr);
        if (cacheData!=null && cacheData.size()>0){
            Map map = new HashMap(2);
            long size = cacheData.size();
            map.put("total",size);
            System.out.println("Total size:"+size);
            int pageNum = reqBean.getPageNum();
            int pageSize = reqBean.getPageSize();
            if (pageNum!=-1 && pageSize!=-1){
                System.out.println("--------------------Start memory paging-------------------");
                List pagination = PageHelper.pagination(cacheData, pageNum, pageSize);
                map.put("data",pagination);
            }else {
                System.out.println("-------------------Get download data------------------");
                map.put("data",cacheData);
            }
            return map;
        }else{
            System.out.println("---------------Unbuffered data----------------");
            new Thread(()->{
                ReqBean rb = new ReqBean();
                rb.setSqlStr(sqlStr);
                rb.setPageNum("-1");
                rb.setPageSize("-1");
                Map<String, Object> map = SqlUtils.queryList(rb);
                if (map!=null && map.size()>0){
                    List<Map> data = (List<Map>) map.get("data");
                    if (data!=null && data.size()>0){
                        PageHelper.setCacheData(sqlStr,data);
                        System.out.println("------------------Data saved to cache successfully-----------------");
                    }else{
                        System.out.println("------------------No data-----------------");
                    }
                }
            }).start();
            System.out.println("-----------------------Query paging initial data-------------------");
            Map<String, Object> map = SqlUtils.queryList(reqBean);
            System.out.println("-------------------End of initial paging data query----------------");
            return map;
        }
    }

    /**
     * @Author lijiale
     * @MethodName insert
     * @Description 新增光缆
     * @Date 16:24 2021/5/8
     * @Version 1.0
     * @param ppticalCable
     * @return: boolean
    **/
    public boolean insert(PpticalCable ppticalCable) throws Exception {
        boolean pbol = false;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String format = dateTimeFormatter.format(now);
        StringBuilder sb = new StringBuilder("insert into gslzorder.O_TR_CABLE_WIRE values (")
                .append("'").append(format).append("',").append("'")
                .append(ppticalCable.getOpticalid()).append("',").append("'")
                .append(ppticalCable.getTestfibrenum()).append("',").append("'")
                .append(ppticalCable.getQualifiedfibrenum()).append("',").append("'")
                .append(ppticalCable.getTestmaxlength()).append("',").append("'")
                .append(ppticalCable.getTestmedianlength()).append("',").append("'")
                .append(ppticalCable.getTestopticalscore()).append("',").append("'")
                .append(ppticalCable.getTestdatascore()).append("',").append("'")
                .append(ppticalCable.getAveragedecay()).append("',").append("'")
                .append(ppticalCable.getLastuploadtime()).append("',").append("'")
                .append(ppticalCable.getReporturl()).append("')");
        pbol = SqlUtils.insert(sb.toString());
        if (pbol){
            List<FibreinfoData> fibreinfos = ppticalCable.getFibreinfos();
            if (fibreinfos!=null && fibreinfos.size()>0){
                StringBuilder sbd = new StringBuilder("insert into gslzorder.O_TR_CABLE_FIBER values ");
                for (FibreinfoData fibreinfo : fibreinfos) {
                    sbd.append("('").append(format).append("',").append("'")
                            .append(fibreinfo.getOpticalid()).append("',").append("'")
                            .append(fibreinfo.getTestfibrenum()).append("',").append("'")
                            .append(fibreinfo.getEndlength()).append("',").append("'")
                            .append(fibreinfo.getConcernrangeaverageloss()).append("',").append("'")
                            .append(fibreinfo.getConcernrangetotalloss()).append("',").append("'")
                            .append(fibreinfo.getAvgloss()).append("',").append("'")
                            .append(fibreinfo.getTotalloss()).append("',").append("'")
                            .append(fibreinfo.getIsborken()).append("'),");
                }
                String sqlStr = sbd.substring(0, sbd.length() - 1);
                pbol = SqlUtils.insert(sqlStr);
            }
        }
        return pbol;
    }
}
