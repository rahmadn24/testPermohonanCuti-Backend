package com.test.permohonanCuti.permohonanCuti.dto;

import java.util.Date;

public class UserLeaveView {

    private Long user_id;
	private Date leave_date_from;
	private Date leave_date_to;
	private String description;
	private String status;
    private String resolver_reason;
    private String resolved_by;
    private Date resolved_date;

    public UserLeaveView(Long user_id, Date leave_date_from, Date leave_date_to, String description, String status, String resolver_reason, String resolved_by, Date resolved_date) {
        this.user_id = user_id;
        this.leave_date_from = leave_date_from;
        this.leave_date_to = leave_date_to;
        this.description = description;
        this.status = status;
        this.resolver_reason = resolver_reason;
        this.resolved_by = resolved_by;
        this.resolved_date = resolved_date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getLeave_date_from() {
        return leave_date_from;
    }

    public void setLeave_date_from(Date leave_date_from) {
        this.leave_date_from = leave_date_from;
    }

    public Date getLeave_date_to() {
        return leave_date_to;
    }

    public void setLeave_date_to(Date leave_date_to) {
        this.leave_date_to = leave_date_to;
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

    public String getResolver_reason() {
        return resolver_reason;
    }

    public void setResolver_reason(String resolver_reason) {
        this.resolver_reason = resolver_reason;
    }

    public String getResolved_by() {
        return resolved_by;
    }

    public void setResolved_by(String resolved_by) {
        this.resolved_by = resolved_by;
    }

    public Date getResolved_date() {
        return resolved_date;
    }

    public void setResolved_date(Date resolved_date) {
        this.resolved_date = resolved_date;
    }
}
