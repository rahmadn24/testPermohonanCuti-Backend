package com.test.permohonanCuti.permohonanCuti.services;

import com.test.permohonanCuti.permohonanCuti.model.Position;
import com.test.permohonanCuti.permohonanCuti.model.PositionLeave;
import com.test.permohonanCuti.permohonanCuti.repository.PositionLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionLeaveServices {

    @Autowired
    PositionLeaveRepository positionLeaveRepository;

    public void inputData(PositionLeave positionLeave) {
        positionLeaveRepository.save(positionLeave);
    }

    public List<PositionLeave> getDataAll() {
        return positionLeaveRepository.findAll();
    }

    public List<PositionLeave> getDataAllById(Long positionLeaveId) {
        return positionLeaveRepository.findByPositionLeaveId(positionLeaveId);
    }

//    public List<PositionLeave> getDataByPosition(Position position) {
//        return positionLeaveRepository.findByPosition(position);
//    }

    public  PositionLeave getDataByPosition(Position position) {
        Optional<PositionLeave> opt = positionLeaveRepository.findByPosition(position);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public PositionLeave getDataById(Long positionLeaveId) {
        Optional<PositionLeave> opt = positionLeaveRepository.findById(positionLeaveId);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public void deleteData(PositionLeave positionLeave) {
        positionLeaveRepository.delete(positionLeave);
    }
}
