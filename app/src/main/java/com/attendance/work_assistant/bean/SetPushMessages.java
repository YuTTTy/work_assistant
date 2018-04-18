package com.attendance.work_assistant.bean;

/**
 * Created by yujx on 2018/4/16.
 */
public class SetPushMessages {
    private DataBean data;
    private String operation;
    private String response;

    public DataBean getData()
    {
        return this.data;
    }

    public String getOperation()
    {
        return this.operation;
    }

    public String getResponse()
    {
        return this.response;
    }

    public void setData(DataBean paramDataBean)
    {
        this.data = paramDataBean;
    }

    public void setOperation(String paramString)
    {
        this.operation = paramString;
    }

    public void setResponse(String paramString)
    {
        this.response = paramString;
    }

    public static class DataBean
    {
        private String Set;

        public String getSet()
        {
            return this.Set;
        }

        public void setSet(String paramString)
        {
            this.Set = paramString;
        }
    }
}