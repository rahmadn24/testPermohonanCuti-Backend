package com.test.permohonanCuti.permohonanCuti.repository;

import com.test.permohonanCuti.permohonanCuti.model.Position;
import com.test.permohonanCuti.permohonanCuti.model.PositionLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionLeaveRepository extends JpaRepository<PositionLeave, Long> {

    List<PositionLeave> findByPositionLeaveId(Long positionLeaveId);
    Optional<PositionLeave> findByPosition(Position position);
}
