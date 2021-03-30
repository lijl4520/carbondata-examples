package com.lijl.carbon.hadoop.utils;

import com.lijl.carbon.hadoop.bd.ConnectionPool;
import com.lijl.carbon.hadoop.bd.CarBonDbPoolManager;
import com.lijl.carbon.hadoop.enums.ConnectionEnum;
import com.lijl.carbon.hadoop.exceptions.ConnectionPoolException;

import java.sql.*;
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

    public static List queryList(String sqlStr) {
        List list = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = CarBonDbPoolManager.newInstance();
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            statement.execute("use gslzorder");
            resultSet = statement.executeQuery(sqlStr);
            list = new ArrayList();
            Map<String,Object> map = new HashMap<>(16);
            while (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object object = resultSet.getObject(columnName);
                    map.put(columnName,object);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (statement!=null){
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException throwables) {
                throw new ConnectionPoolException(ConnectionEnum.CONNECTION_CLOSE_FAIL);
            }
        }
        return list;
    }
}
