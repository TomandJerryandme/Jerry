package com.neuedu.vo;

//在封装成json数据时，忽略值为null的字段
public class ServerResponse<T> {
    private int status;

    //在封装成json数据时，忽略值为null的字段
    private T data;
    private String msg;

    public ServerResponse(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
