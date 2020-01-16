package com.test.permohonanCuti.permohonanCuti.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.permohonanCuti.permohonanCuti.model.Position;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

public class UserDto {
    private Long userId;
    private String userName;
    private Integer leaveAvaible;
    private String createdBy;
    private String updateBy;
    private String positionName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLeaveAvaible() {
        return leaveAvaible;
    }

    public void setLeaveAvaible(Integer leaveAvaible) {
        this.leaveAvaible = leaveAvaible;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionId(String positionName) {
        this.positionName = positionName;
    }
}
