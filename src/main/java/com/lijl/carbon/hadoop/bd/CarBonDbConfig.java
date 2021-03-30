package com.lijl.carbon.hadoop.bd;

/**
 * @Author Lijl
 * @ClassName CarBonDbConfig
 * @Description 连接池实体
 * @Date 2021/3/29 17:10
 * @Version 1.0
 */
public class CarBonDbConfig {
    /**
     * 连接驱动
     */
    private String driver;
    /**
     * 连接地址
     */
    private String url;
    /**
     * 空闲连接池，最小连接数，默认为2
     */
    private final Integer MIN_FREE_CONNECTIONS = 2;
    private Integer minFreeConnections = MIN_FREE_CONNECTIONS;
    /**
     * 空闲连接池，最大连接数，默认为8
     */
    private final static Integer MAX_FREE_CONNECTIONS = 8;
    private Integer maxFreeConnections = MAX_FREE_CONNECTIONS;
    /**
     * 活跃连接池，最大连接数，默认为8
     */
    private final Integer MAX_ACTIVE_CONNECTIONS = 8;
    private Integer maxActiveConnection = MAX_ACTIVE_CONNECTIONS;
    /**
     * 初始化连接数，默认为2个
     */
    private final Integer INIT_CONNECTIONS = 2;
    private Integer initConnections = INIT_CONNECTIONS;
    /**
     * 连接超时时间，默认为20分钟
     */
    private final Long CONNECTION_TIME_OUT = 1000*60*20L;
    private Long connectionTimeOut = CONNECTION_TIME_OUT;
    /**
     * 自检循环时间，默认为60秒
     */
    private final Long RECHECK_TIME = 1000*60L;
    private Long recheckTime = RECHECK_TIME;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMinFreeConnections() {
        return minFreeConnections;
    }

    public void setMinFreeConnections(Integer minFreeConnections) {
        this.minFreeConnections = minFreeConnections;
    }

    public Integer getMaxFreeConnections() {
        return maxFreeConnections;
    }

    public void setMaxFreeConnections(Integer maxFreeConnections) {
        this.maxFreeConnections = maxFreeConnections;
    }

    public Integer getMaxActiveConnection() {
        return maxActiveConnection;
    }

    public void setMaxActiveConnection(Integer maxActiveConnection) {
        this.maxActiveConnection = maxActiveConnection;
    }

    public Integer getInitConnections() {
        return initConnections;
    }

    public void setInitConnections(Integer initConnections) {
        this.initConnections = initConnections;
    }

    public Long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(Long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public Long getRecheckTime() {
        return recheckTime;
    }

    public void setRecheckTime(Long recheckTime) {
        this.recheckTime = recheckTime;
    }
}