package com.zl.property.model.dto;

public class ResultDto {
    private Boolean isSuccess = true;
    private Boolean hasSuccess = false;
    private String errMessage = "";
    private Object data;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Boolean getHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(Boolean hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
