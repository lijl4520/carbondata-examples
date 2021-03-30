package com.lijl.carbon.hadoop.security;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.*;

/**
 * @Author Lijl
 * @ClassName KerberosAuth
 * @Description Kerberos认证
 * @Date 2021/3/30 14:54
 * @Version 1.0
 */
public class KerberosAuth {

    private static final String userPrincipal = "gslzorder01";
    private static final String  userKeytabPath = "/opt/hdfsclient/gslzorder01.keytab";
    private static final String  krb5ConfPath = "/opt/hdfsclient/krb5.conf";
    private static final String  ZKServerPrincipal = "spark2x/hadoop.hadoop.com@HADOOP.COM";
    private static final String  ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME = "Client";
    private static final String  ZOOKEEPER_SERVER_PRINCIPAL_KEY = "zookeeper.server.principal";
    private static final String  securityConfig = ";saslQop=auth-conf;auth=KERBEROS;principal=spark2x/hadoop.hadoop.com@HADOOP.COM;user.principal=gslzorder01;user.keytab=/opt/hdfsclient/gslzorder01.keytab;";
    private static final String  sparkConfPath = "/opt/hdfsclient/Spark2x/spark/conf/spark-defaults.conf";
    private static final String  hiveConfig = "/opt/hdfsclient/Spark2x/spark/conf/hive-site.xml";
    public static String globalURL= "";

    public static void authKerberos() throws IOException {
        Configuration hadoopConf = new Configuration();
        LoginUtil.setJaasConf(ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME, userPrincipal, userKeytabPath);
        LoginUtil.setZookeeperServerPrincipal(ZOOKEEPER_SERVER_PRINCIPAL_KEY, ZKServerPrincipal);
        LoginUtil.login(userPrincipal, userKeytabPath, krb5ConfPath, hadoopConf);
        Configuration config = new Configuration();
        config.addResource(new Path(hiveConfig));
        String zkUrl = config.get("spark.deploy.zookeeper.url");
        Properties fileInfo = null;
        InputStream fileInputStream = null;
        try {
            fileInfo = new Properties();
            File propertiesFile = new File(sparkConfPath);
            fileInputStream = new FileInputStream(propertiesFile);
            fileInfo.load(fileInputStream);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

        String zkNamespace = null;
        zkNamespace = fileInfo.getProperty("spark.thriftserver.zookeeper.namespace");
        if (zkNamespace != null && !"".equals(zkNamespace)) {
            zkNamespace = zkNamespace.substring(1);
        }

        StringBuilder sb = new StringBuilder("jdbc:hive2://").append(zkUrl).append(";serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=")
                .append(zkNamespace).append(securityConfig);
                /*+ zkUrl
                + ";serviceDiscoveryMode=zooKeeper;zooKeeperNamespace="
                + zkNamespace
                + securityConfig);*/
        globalURL = sb.toString();
        System.out.println("==========globalURL======="+globalURL);
        /*try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            Connection connection = DriverManager.getConnection(globalURL);
            System.out.println("=======CreateConnection create dbconn ok===" + connection);
            Statement statement = connection.createStatement();
            statement.execute("use gslzorder");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gslzorder.DM_LTE_STAN_EUC_15M_CARBON LIMIT 1");
            List list = new ArrayList();
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
            System.out.println(list.toString());
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }*/
    }
}
