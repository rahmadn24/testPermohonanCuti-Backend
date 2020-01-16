package com.test.permohonanCuti.permohonanCuti.dto;

import java.util.Date;

public class UserLeaveView {
    Long userId;
	Date leaveDateFrom;
	Date leaveDateTo;
	String description;
	String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(Date leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public Date getLeaveDateTo() {
        return leaveDateTo;
    }

    public void setLeaveDateTo(Date leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
