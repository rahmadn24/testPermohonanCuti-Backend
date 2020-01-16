package com.test.permohonanCuti.permohonanCuti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="UserLeaveRequest")
public class UserLeaveRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_leave_request_id", unique = true, nullable = false)
    private Long userLeaveRequestId;

    @Column(name = "leave_date_from")
    private Date leaveDateFrom;

    @Column(name = "leave_date_to")
    private Date leaveDateTo;

    @Column(length=30, name = "status")
    private String status;

    @Column(name = "description")
    private String description;

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

    @Column(name = "last_update_by")
    private String updateBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
