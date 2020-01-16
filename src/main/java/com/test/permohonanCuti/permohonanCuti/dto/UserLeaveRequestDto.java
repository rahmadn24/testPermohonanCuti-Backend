package com.test.permohonanCuti.permohonanCuti.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

public class UserLeaveRequestDto {
    private Long userLeaveRequestId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date leaveDateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date leaveDateTo;

    private String description;
    private Long user_id;

    public Long getUserLeaveRequestId() {
        return userLeaveRequestId;
    }

    public void setUserLeaveRequestId(Long userLeaveRequestId) {
        this.userLeaveRequestId = userLeaveRequestId;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
