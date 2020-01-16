package com.test.permohonanCuti.permohonanCuti.services;

import com.test.permohonanCuti.permohonanCuti.model.Position;
import com.test.permohonanCuti.permohonanCuti.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServices {
    @Autowired
    PositionRepository positionRepository;

    public void inputData(Position position) {
        positionRepository.save(position);
    }

    public List<Position> getDataAll() {
        return positionRepository.findAll();
    }

    public Position getDataByPositionName(String positionName) {
        Optional<Position> opt = positionRepository.findByPositionName(positionName);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public void deleteData(Position user) {
        positionRepository.delete(user);
    }
}
