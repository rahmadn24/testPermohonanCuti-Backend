package com.test.permohonanCuti.permohonanCuti.repository;

import com.test.permohonanCuti.permohonanCuti.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByPositionName(String positionName);
}
