package com.test.permohonanCuti.permohonanCuti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Position_leave")
@JsonIgnoreProperties(value = {"createAt", "updateAt"}, allowGetters = true)
public class PositionLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="position_leave_id", unique = true, nullable = false)
    private Long positionLeaveId;

    @Column(name = "leave_approve")
    private Integer leaveApprove;

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
    @JoinColumn(name="position_id", nullable=true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
    private Position position;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
