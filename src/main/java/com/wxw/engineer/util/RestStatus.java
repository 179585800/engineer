package com.wxw.engineer.util;

public class RestStatus
{
    public static final int OK = 200;
    public static final int PARAMETER_ERROR = 3001;
    public static final int PARAMETER_NOT_MEET = 3002;
    public static final int OBJECT_NULL = 4004;
    public static final int OBJECT_EXISTED = 4005;
    public static final int ADD_FAILED = 2014;
    public static final int UPDATE_FAILED = 2024;
    public static final int DELETE_FAILED = 2034;
    public static final int NOT_LOGGED_IN = 1400;
    public static final int NO_PERMISSIONS = 1401;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NETWORK_ERROR = 5003;

    public RestStatus()
    {
    }
}