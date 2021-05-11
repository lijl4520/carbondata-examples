package com.lijl.carbon.hadoop.utils;

import com.lijl.carbon.hadoop.bd.ConnectionPool;
import com.lijl.carbon.hadoop.bd.CarBonDbPoolManager;
import com.lijl.carbon.pojo.FibreinfoData;
import com.lijl.carbon.pojo.PpticalCable;
import com.lijl.carbon.utils.ReqBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Lijl
 * @ClassName SqlUtils
 * @Description jdbc查询
 * @Date 2021/3/30 14:08
 * @Version 1.0
 */
public class SqlUtils {

    /**
     * @Author lijiale
     * @MethodName queryList
     * @Description 查询数据
     * @Date 14:30 2021/4/12
     * @Version 1.0
     * @param reqBean
     * @return: java.util.Map<java.lang.String,java.lang.Object>
    **/
    public static Map<String,Object> queryList(ReqBean reqBean) {
        List list = null;
        Map<String,Object> retMap = new HashMap<>(2);
        Statement statement = null;
        ResultSet countRestlt = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        long total = 0;
        try {
            String sqlStr = reqBean.getSqlStr();
            int pageNum = reqBean.getPageNum();
            int pageSize = reqBean.getPageSize();
            connectionPool = CarBonDbPoolManager.newInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            if (pageNum!=-1 && pageSize != -1){
                String queryCountSql = new StringBuffer("SELECT COUNT(1) TOTAL FROM (").append(sqlStr).append(") T").toString();
                System.out.println("统计条件查询数量SQL："+queryCountSql);
                countRestlt = statement.executeQuery(queryCountSql);
                while (countRestlt.next()){
                    total = countRestlt.getLong("TOTAL");
                }
                retMap.put("total",total);
                close(null,countRestlt,null);
            }
            if ((pageNum == -1 && pageSize == -1) || (total>0)){
                StringBuilder sb = new StringBuilder(sqlStr).append(" ORDER BY TIME_ID");
                if (pageNum!=-1 && pageSize != -1){
                    sb.append(" LIMIT ").append(pageSize);
                }
                String sbStr = sb.toString();
                resultSet = statement.executeQuery(sbStr);
                list = new ArrayList();
                while (resultSet.next()){
                    Map<String,Object> map = new HashMap<>(16);
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        String columnName = metaData.getColumnName(i + 1);
                        Object object = resultSet.getObject(columnName);
                        map.put(columnName,object);
                    }
                    list.add(map);
                }
            }
            retMap.put("data",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(statement,countRestlt,resultSet);
            connectionPool.releaseConnection(connection);
        }
        return retMap;
    }


    /**
     * @Author lijiale
     * @MethodName insert
     * @Description 新增
     * @Date 17:01 2021/5/8
     * @Version 1.0
     * @param sql
     * @return: boolean
    **/
    public static boolean insert(String sql){
        ConnectionPool connectionPool = null;
        Connection connection = null;
        Statement ps = null;
        boolean bol = false;
        try {
            connectionPool = CarBonDbPoolManager.newInstance();
            connection = connectionPool.getConnection();
            ps = connection.createStatement();
            bol = ps.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(ps,null,null);
            connectionPool.releaseConnection(connection);
        }
        return bol;
    }

    /**
     * @Author lijiale
     * @MethodName close
     * @Description 释放资源
     * @Date 14:31 2021/4/12
     * @Version 1.0
     * @param statement
     * @param countRestlt
     * @param resultSet
     * @return: void
    **/
    private static void close(Statement statement,ResultSet countRestlt,ResultSet resultSet){
        try {
            if (countRestlt!=null){
                countRestlt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet!=null){
                resultSet.close();;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
