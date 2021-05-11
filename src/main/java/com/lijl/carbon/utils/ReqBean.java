package com.lijl.carbon.utils;

/**
 * @Author Lijl
 * @ClassName ReqBean
 * @Description 参数实体
 * @Date 2021/4/12 14:11
 * @Version 1.0
 */
public class ReqBean {
    private String sqlStr;
    private String pageNum;
    private String pageSize;

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public int getPageNum() {
        if (pageNum!=null && !"".equals(pageNum)){
            int pageNum = Integer.parseInt(this.pageNum);
            int var = -1;
            if (pageNum == var){
                return var;
            }
            return pageNum;
        }
        return 0;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        if (pageSize!=null && !"".equals(pageSize)){
            return Integer.parseInt(pageSize);
        }
        return 0;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
