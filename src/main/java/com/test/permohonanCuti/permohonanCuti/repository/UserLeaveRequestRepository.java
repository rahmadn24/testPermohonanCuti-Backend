package com.test.permohonanCuti.permohonanCuti.repository;

import com.test.permohonanCuti.permohonanCuti.dto.UserLeaveView;
import com.test.permohonanCuti.permohonanCuti.model.UserLeaveRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.awt.print.Pageable;
import java.util.List;

public interface UserLeaveRequestRepository extends JpaRepository<UserLeaveRequest, Long> {
    @Query("select new com.test.permohonanCuti.permohonanCuti.dto.UserLeaveView(p.user.userId,p.leaveDateFrom,p.leaveDateTo," +
            "p.description,p.status,m.resolverReason, m.resolvedBy, m.resolvedDate) FROM UserLeaveRequest as p \n" +
            "inner join BucketApproval as m on p.userLeaveRequestId = m.userLeaveRequest\n" +
            "where p.user.userId = :users")
    List<UserLeaveView> findByByUsers(Long users, Pageable pageable);
}
