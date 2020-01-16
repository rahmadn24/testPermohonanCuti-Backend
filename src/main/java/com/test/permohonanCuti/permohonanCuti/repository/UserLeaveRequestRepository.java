package com.test.permohonanCuti.permohonanCuti.repository;

import com.test.permohonanCuti.permohonanCuti.dto.UserLeaveView;
import com.test.permohonanCuti.permohonanCuti.model.User;
import com.test.permohonanCuti.permohonanCuti.model.UserLeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserLeaveRequestRepository extends JpaRepository<UserLeaveRequest, Long> {
//    @Query("select p.user.userId,p.leaveDateFrom,p.leaveDateTo,p.description,p.status,\n" +
//            "m.resolverReason, m.resolvedBy, m.resolvedDate FROM UserLeaveRequest as p \n" +
//            "inner join BucketApproval as m on p.userLeaveRequestId = m.userLeaveRequest\n" +
//            "where p.user.userId = :users")

    @Query("select p.user.userId,p.leaveDateFrom,p.leaveDateTo,p.description,p.status\n" +
            "FROM UserLeaveRequest as p \n" +
            "inner join BucketApproval as m on p.userLeaveRequestId = m.userLeaveRequest\n" +
            "where p.user.userId = :users")
    List<UserLeaveRequest> findByByUsers(Long users, Pageable pageable);
}
