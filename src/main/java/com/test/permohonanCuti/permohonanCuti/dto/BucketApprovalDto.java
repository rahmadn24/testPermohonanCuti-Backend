package com.test.permohonanCuti.permohonanCuti.dto;

import java.util.Date;

public class BucketApprovalDto {
    private Long bucketApprovalId;
    private String resolverReason;
    private String resolvedBy;
    private Date resolvedDate;
    private Date requestDate;
    private String userRequestName;
    private String createdBy;
    private String updateBy;
    private Long userLeaveRequest;

    public Long getBucketApprovalId() {
        return bucketApprovalId;
    }

    public void setBucketApprovalId(Long bucketApprovalId) {
        this.bucketApprovalId = bucketApprovalId;
    }

    public String getResolverReason() {
        return resolverReason;
    }

    public void setResolverReason(String resolverReason) {
        this.resolverReason = resolverReason;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getUserRequestName() {
        return userRequestName;
    }

    public void setUserRequestName(String userRequestName) {
        this.userRequestName = userRequestName;
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

    public Long getUserLeaveRequest() {
        return userLeaveRequest;
    }

    public void setUserLeaveRequest(Long userLeaveRequest) {
        this.userLeaveRequest = userLeaveRequest;
    }
}
