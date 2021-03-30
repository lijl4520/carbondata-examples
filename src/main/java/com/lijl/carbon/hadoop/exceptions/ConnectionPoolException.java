package com.lijl.carbon.hadoop.exceptions;

import com.lijl.carbon.hadoop.enums.ConnectionEnum;
/**
 * @Author Lijl
 * @ClassName ConnectionPoolException
 * @Description 连接异常
 * @Date 2021/3/29 17:17
 * @Version 1.0
 */
public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public ConnectionPoolException() {
        super();
        code=0;
    }
    public ConnectionPoolException(ConnectionEnum connectionEnum) {
        super(connectionEnum.getMsg());
        this.code=connectionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}


