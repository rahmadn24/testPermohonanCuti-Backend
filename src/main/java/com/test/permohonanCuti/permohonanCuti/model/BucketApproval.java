package com.test.permohonanCuti.permohonanCuti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BucketApproval")
public class BucketApproval implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bucket_approval_id", unique = true, nullable = false)
    private Long bucketApprovalId;

    @Column(length=100, name = "resolverReason")
    private String resolverReason;

    @Column(length=100, name = "resolvedBy")
    private String resolvedBy;

    @Column(length=100, name = "resolvedDate")
    private Date resolvedDate;

    @Column(length=100, name = "requestDate")
    private Date requestDate;

    @Column(name = "created_date", updatable = false)
    @Temporal (TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "update_date")
    @Temporal (TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_leave_request_id", nullable=true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserLeaveRequest userLeaveRequest;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public UserLeaveRequest getUserLeaveRequest() {
        return userLeaveRequest;
    }

    public void setUserLeaveRequest(UserLeaveRequest userLeaveRequest) {
        this.userLeaveRequest = userLeaveRequest;
    }
}
