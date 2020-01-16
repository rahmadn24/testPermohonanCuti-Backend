package com.test.permohonanCuti.permohonanCuti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Users")
@JsonIgnoreProperties(value = {"createAt", "updateAt"}, allowGetters = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id", unique = true, nullable = false)
    private Long userId;

    @Column(length=30, name = "user_name")
    private String userName;

    @Column(name = "leave_avaible")
    private Integer leaveAvaible;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="position_id", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
    private Position position;

    public Long getIdUser() {
        return userId;
    }

    public void setIdUser(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLeaveAvaible() {
        return leaveAvaible;
    }

    public void setLeaveAvaible(Integer leaveAvaible) {
        this.leaveAvaible = leaveAvaible;
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
