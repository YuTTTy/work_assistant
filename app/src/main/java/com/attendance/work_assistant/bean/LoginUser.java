package com.attendance.work_assistant.bean;

/**
 * Created by yujx on 2018/4/16.
 */
public class LoginUser {
    private DataBean data;
    private String login;
    private String response;

    public DataBean getData()
    {
        return this.data;
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getResponse()
    {
        return this.response;
    }

    public void setData(DataBean paramDataBean)
    {
        this.data = paramDataBean;
    }

    public void setLogin(String paramString)
    {
        this.login = paramString;
    }

    public void setResponse(String paramString)
    {
        this.response = paramString;
    }

    public static class DataBean
    {
        private String AvatarImage;
        private String CrmUserID;
        private String DepartFullName;
        private String Email;
        private String EntryDate;
        private String Job;
        private String LoginCode;
        private String Mobile;
        private String OAcookieName;
        private String OAcookieValue;
        private int OfficeID;
        private String OfficeLocation;
        private String OfficeStation;
        private String Permissions;
        private String ServerTime;
        private String Sex;
        private String Telephone;
        private String TrueName;
        private String UserID;

        public String getAvatarImage()
        {
            return this.AvatarImage;
        }

        public String getCrmUserID()
        {
            return this.CrmUserID;
        }

        public String getDepartFullName()
        {
            return this.DepartFullName;
        }

        public String getEmail()
        {
            return this.Email;
        }

        public String getEntryDate()
        {
            return this.EntryDate;
        }

        public String getJob()
        {
            return this.Job;
        }

        public String getLoginCode()
        {
            return this.LoginCode;
        }

        public String getMobile()
        {
            return this.Mobile;
        }

        public String getOAcookieName()
        {
            return this.OAcookieName;
        }

        public String getOAcookieValue()
        {
            return this.OAcookieValue;
        }

        public int getOfficeID()
        {
            return this.OfficeID;
        }

        public String getOfficeLocation()
        {
            return this.OfficeLocation;
        }

        public String getOfficeStation()
        {
            return this.OfficeStation;
        }

        public String getPermissions()
        {
            return this.Permissions;
        }

        public String getServerTime()
        {
            return this.ServerTime;
        }

        public String getSex()
        {
            return this.Sex;
        }

        public String getTelephone()
        {
            return this.Telephone;
        }

        public String getTrueName()
        {
            return this.TrueName;
        }

        public String getUserID()
        {
            return this.UserID;
        }

        public void setAvatarImage(String paramString)
        {
            this.AvatarImage = paramString;
        }

        public void setCrmUserID(String paramString)
        {
            this.CrmUserID = paramString;
        }

        public void setDepartFullName(String paramString)
        {
            this.DepartFullName = paramString;
        }

        public void setEmail(String paramString)
        {
            this.Email = paramString;
        }

        public void setEntryDate(String paramString)
        {
            this.EntryDate = paramString;
        }

        public void setJob(String paramString)
        {
            this.Job = paramString;
        }

        public void setLoginCode(String paramString)
        {
            this.LoginCode = paramString;
        }

        public void setMobile(String paramString)
        {
            this.Mobile = paramString;
        }

        public void setOAcookieName(String paramString)
        {
            this.OAcookieName = paramString;
        }

        public void setOAcookieValue(String paramString)
        {
            this.OAcookieValue = paramString;
        }

        public void setOfficeID(int paramInt)
        {
            this.OfficeID = paramInt;
        }

        public void setOfficeLocation(String paramString)
        {
            this.OfficeLocation = paramString;
        }

        public void setOfficeStation(String paramString)
        {
            this.OfficeStation = paramString;
        }

        public void setPermissions(String paramString)
        {
            this.Permissions = paramString;
        }

        public void setServerTime(String paramString)
        {
            this.ServerTime = paramString;
        }

        public void setSex(String paramString)
        {
            this.Sex = paramString;
        }

        public void setTelephone(String paramString)
        {
            this.Telephone = paramString;
        }

        public void setTrueName(String paramString)
        {
            this.TrueName = paramString;
        }

        public void setUserID(String paramString)
        {
            this.UserID = paramString;
        }
    }
}