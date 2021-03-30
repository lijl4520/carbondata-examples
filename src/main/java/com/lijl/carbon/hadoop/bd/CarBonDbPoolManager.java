package com.lijl.carbon.hadoop.bd;

import com.lijl.carbon.hadoop.security.KerberosAuth;

import java.io.IOException;

/**
 * @Author Lijl
 * @ClassName CarBonDbPoolManager
 * @Description 单例连接池管理类
 * @Date 2021/3/29 17:19
 * @Version 1.0
 */
public class CarBonDbPoolManager {
    private static class Builder{
        //1.加载jdbc配置文件，配置连接池信息
        private static CarBonDbConfig dbConfig=null;
        static{
            try {
                KerberosAuth.authKerberos();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dbConfig=new CarBonDbConfig();
            dbConfig.setUrl(KerberosAuth.globalURL);
            dbConfig.setDriver("org.apache.hive.jdbc.HiveDriver");
        }
        //2.创建连接池对象
        public static ConnectionPool connectionPool=new ConnectionPool(dbConfig);
    }
    public static ConnectionPool newInstance(){
        return Builder.connectionPool;
    }
}


