package com.attendance.work_assistant.bean;

import java.util.List;

/**
 * Created by yujx on 2018/4/16.
 */
public class PermissionBean {
        private DataBean data;
        private MessagesetBean messageset;
        private String operation;
        private PendingBean pending;
        private String response;

        public DataBean getData() {
            return this.data;
        }

        public MessagesetBean getMessageset() {
            return this.messageset;
        }

        public String getOperation() {
            return this.operation;
        }

        public PendingBean getPending() {
            return this.pending;
        }

        public String getResponse() {
            return this.response;
        }

        public void setData(DataBean paramDataBean) {
            this.data = paramDataBean;
        }

        public void setMessageset(MessagesetBean paramMessagesetBean) {
            this.messageset = paramMessagesetBean;
        }

        public void setOperation(String paramString) {
            this.operation = paramString;
        }

        public void setPending(PendingBean paramPendingBean) {
            this.pending = paramPendingBean;
        }

        public void setResponse(String paramString) {
            this.response = paramString;
        }

        public static class DataBean {
            private String AddContactPermissions;
            private String AddVisitReturnPermissions;
            private String AttendCheckPermission;
            private String BBSIsAdministratorPermission;
            private String BackMoneyDepartConfirmPermission;
            private String BackMoneyFinanceConfirmPermission;
            private String BackMoneyPermission;
            private String BackMoneySalesConfirmPermission;
            private String BackMoneySalesDismissAdjustmentPermission;
            private String BusinessApprovalPermission;
            private String ContactPermission;
            private String CustomApplyLockPermission;
            private String CustomApplyOpenPermission;
            private String CustomApplyPropellingPermission;
            private String CustomApplyRevokeBlackPermission;
            private String CustomApplyStopPermission;
            private String CustomPermission;
            private String DashboardPermission;
            private String InvoicePermission;
            private String OrderPermission;
            private String StatisticsPermission;
            private String VisitReturnPermission;
            private String WorkReportPermission;

            public String getAddContactPermissions() {
                return this.AddContactPermissions;
            }

            public String getAddVisitReturnPermissions() {
                return this.AddVisitReturnPermissions;
            }

            public String getAttendCheckPermission() {
                return this.AttendCheckPermission;
            }

            public String getBBSIsAdministratorPermission() {
                return this.BBSIsAdministratorPermission;
            }

            public String getBackMoneyDepartConfirmPermission() {
                return this.BackMoneyDepartConfirmPermission;
            }

            public String getBackMoneyFinanceConfirmPermission() {
                return this.BackMoneyFinanceConfirmPermission;
            }

            public String getBackMoneyPermission() {
                return this.BackMoneyPermission;
            }

            public String getBackMoneySalesConfirmPermission() {
                return this.BackMoneySalesConfirmPermission;
            }

            public String getBackMoneySalesDismissAdjustmentPermission() {
                return this.BackMoneySalesDismissAdjustmentPermission;
            }

            public String getBusinessApprovalPermission() {
                return this.BusinessApprovalPermission;
            }

            public String getContactPermission() {
                return this.ContactPermission;
            }

            public String getCustomApplyLockPermission() {
                return this.CustomApplyLockPermission;
            }

            public String getCustomApplyOpenPermission() {
                return this.CustomApplyOpenPermission;
            }

            public String getCustomApplyPropellingPermission() {
                return this.CustomApplyPropellingPermission;
            }

            public String getCustomApplyRevokeBlackPermission() {
                return this.CustomApplyRevokeBlackPermission;
            }

            public String getCustomApplyStopPermission() {
                return this.CustomApplyStopPermission;
            }

            public String getCustomPermission() {
                return this.CustomPermission;
            }

            public String getDashboardPermission() {
                return this.DashboardPermission;
            }

            public String getInvoicePermission() {
                return this.InvoicePermission;
            }

            public String getOrderPermission() {
                return this.OrderPermission;
            }

            public String getStatisticsPermission() {
                return this.StatisticsPermission;
            }

            public String getVisitReturnPermission() {
                return this.VisitReturnPermission;
            }

            public String getWorkReportPermission() {
                return this.WorkReportPermission;
            }

            public void setAddContactPermissions(String paramString) {
                this.AddContactPermissions = paramString;
            }

            public void setAddVisitReturnPermissions(String paramString) {
                this.AddVisitReturnPermissions = paramString;
            }

            public void setAttendCheckPermission(String paramString) {
                this.AttendCheckPermission = paramString;
            }

            public void setBBSIsAdministratorPermission(String paramString) {
                this.BBSIsAdministratorPermission = paramString;
            }

            public void setBackMoneyDepartConfirmPermission(String paramString) {
                this.BackMoneyDepartConfirmPermission = paramString;
            }

            public void setBackMoneyFinanceConfirmPermission(String paramString) {
                this.BackMoneyFinanceConfirmPermission = paramString;
            }

            public void setBackMoneyPermission(String paramString) {
                this.BackMoneyPermission = paramString;
            }

            public void setBackMoneySalesConfirmPermission(String paramString) {
                this.BackMoneySalesConfirmPermission = paramString;
            }

            public void setBackMoneySalesDismissAdjustmentPermission(String paramString) {
                this.BackMoneySalesDismissAdjustmentPermission = paramString;
            }

            public void setBusinessApprovalPermission(String paramString) {
                this.BusinessApprovalPermission = paramString;
            }

            public void setContactPermission(String paramString) {
                this.ContactPermission = paramString;
            }

            public void setCustomApplyLockPermission(String paramString) {
                this.CustomApplyLockPermission = paramString;
            }

            public void setCustomApplyOpenPermission(String paramString) {
                this.CustomApplyOpenPermission = paramString;
            }

            public void setCustomApplyPropellingPermission(String paramString) {
                this.CustomApplyPropellingPermission = paramString;
            }

            public void setCustomApplyRevokeBlackPermission(String paramString) {
                this.CustomApplyRevokeBlackPermission = paramString;
            }

            public void setCustomApplyStopPermission(String paramString) {
                this.CustomApplyStopPermission = paramString;
            }

            public void setCustomPermission(String paramString) {
                this.CustomPermission = paramString;
            }

            public void setDashboardPermission(String paramString) {
                this.DashboardPermission = paramString;
            }

            public void setInvoicePermission(String paramString) {
                this.InvoicePermission = paramString;
            }

            public void setOrderPermission(String paramString) {
                this.OrderPermission = paramString;
            }

            public void setStatisticsPermission(String paramString) {
                this.StatisticsPermission = paramString;
            }

            public void setVisitReturnPermission(String paramString) {
                this.VisitReturnPermission = paramString;
            }

            public void setWorkReportPermission(String paramString) {
                this.WorkReportPermission = paramString;
            }
        }

        public static class MessagesetBean {
            private List<String> TimeSeting;
            private List<List<String>> TypeSeting;

            public List<String> getTimeSeting() {
                return this.TimeSeting;
            }

            public List<List<String>> getTypeSeting() {
                return this.TypeSeting;
            }

            public void setTimeSeting(List<String> paramList) {
                this.TimeSeting = paramList;
            }

            public void setTypeSeting(List<List<String>> paramList) {
                this.TypeSeting = paramList;
            }
        }

        public static class PendingBean {
            private int AttendPendingCount;
            private int BusinessPendingCount;
            private int PushMessagePendingCount;

            public int getAttendPendingCount() {
                return this.AttendPendingCount;
            }

            public int getBusinessPendingCount() {
                return this.BusinessPendingCount;
            }

            public int getPushMessagePendingCount() {
                return this.PushMessagePendingCount;
            }

            public void setAttendPendingCount(int paramInt) {
                this.AttendPendingCount = paramInt;
            }

            public void setBusinessPendingCount(int paramInt) {
                this.BusinessPendingCount = paramInt;
            }

            public void setPushMessagePendingCount(int paramInt) {
                this.PushMessagePendingCount = paramInt;
            }
        }
    }
