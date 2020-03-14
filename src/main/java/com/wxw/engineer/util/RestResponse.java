package com.wxw.engineer.util;

public class RestResponse<T>
{
    private int status;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    private String errMsg;
    private T data;

    public RestResponse()
    {

    }

    public RestResponse(int status)
    {
        this.status = status;

    }

    public RestResponse(int status, String errMsg)
    {
        this.status = status;
        this.errMsg = errMsg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public RestResponse(int status, T data)
    {
        this.status = status;
        this.data = data;
    }

    public RestResponse(int status, T data, String errMsg)
    {
        this.status = status;
        this.data = data;
        this.errMsg = errMsg;
    }

}
