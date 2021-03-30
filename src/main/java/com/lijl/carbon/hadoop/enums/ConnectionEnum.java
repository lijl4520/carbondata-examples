package com.lijl.carbon.hadoop.enums;

/**
 * @Author Lijl
 * @EnumName ResultEnum
 * @Description TODO
 * @Date 2021/3/29 17:15
 * @Version 1.0
 */
public enum ConnectionEnum {

    DRIVER_NOT_FOUND(10000,"加载驱动失败"),
    CONNECTION_FAIL(10001,"建立连接失败"),
    CONNECTION_STATUS_ERROE(10002,"连接状态异常"),
    CONNECTION_CLOSE_FAIL(10003,"连接关闭失败"),
    INTERRUPT_ERROR(10004,"阻塞异常");

    private Integer code;
    private String msg;
    ConnectionEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    ConnectionEnum(){}
    public String getMsg() {
        return msg;
    }
    public Integer getCode() {
        return code;
    }
}

