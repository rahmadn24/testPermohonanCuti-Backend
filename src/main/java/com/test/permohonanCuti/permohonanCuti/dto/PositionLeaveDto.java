package com.test.permohonanCuti.permohonanCuti.dto;

public class PositionLeaveDto {
    private Long positionLeaveId;
    private Integer leaveApprove;
    private String createdBy;
    private String updateBy;
    private String positionName;

    public Long getPositionLeaveId() {
        return positionLeaveId;
    }

    public void setPositionLeaveId(Long positionLeaveId) {
        this.positionLeaveId = positionLeaveId;
    }

    public Integer getLeaveApprove() {
        return leaveApprove;
    }

    public void setLeaveApprove(Integer leaveApprove) {
        this.leaveApprove = leaveApprove;
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

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
