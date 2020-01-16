package com.test.permohonanCuti.permohonanCuti.controller;

import com.test.permohonanCuti.permohonanCuti.dto.PositionDto;
import com.test.permohonanCuti.permohonanCuti.dto.PositionLeaveDto;
import com.test.permohonanCuti.permohonanCuti.model.Position;
import com.test.permohonanCuti.permohonanCuti.model.PositionLeave;
import com.test.permohonanCuti.permohonanCuti.repository.PositionLeaveRepository;
import com.test.permohonanCuti.permohonanCuti.services.PositionLeaveServices;
import com.test.permohonanCuti.permohonanCuti.services.PositionServices;
import com.test.permohonanCuti.permohonanCuti.utils.Constant;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/positionLeave")
public class PositionLeaveController {

    @Autowired
    PositionLeaveServices positionLeaveServices;

    @Autowired
    PositionServices positionServices;

    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger  = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getData() {
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<PositionLeave> list = positionLeaveServices.getDataAll();
            data.put(Constant.CONST_DATA, list);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{positionLeaveId}")
    public ResponseEntity<HashMap<String, Object>> getDataById(@PathVariable(value="positionLeaveId") Long positionLeaveId) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<PositionLeave> list = positionLeaveServices.getDataAllById(positionLeaveId);
            data.put(Constant.CONST_DATA, list);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> insertData(@RequestBody PositionLeaveDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        PositionLeave positionLeave = modelMapper.map(dto, PositionLeave.class);
        try {
            positionLeave.setPosition(positionServices.getDataByPositionName(dto.getPositionName()));
            positionLeaveServices.inputData(positionLeave);
            data.put(Constant.CONST_DATA, positionLeave);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> updateData(@RequestBody PositionLeaveDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            PositionLeave positionLeave = positionLeaveServices.getDataById(dto.getPositionLeaveId());
            positionLeave.setLeaveApprove(dto.getLeaveApprove() != null ? dto.getLeaveApprove() : positionLeave.getLeaveApprove());
            positionLeave.setPosition(dto.getPositionName() != null ? positionServices.getDataByPositionName(dto.getPositionName()) : positionLeave.getPosition());
            positionLeave.setUpdateBy(dto.getUpdateBy() != null ? dto.getUpdateBy() : positionLeave.getUpdateBy());
            positionLeave.setUpdateDate(new Date());
            positionLeaveServices.inputData(positionLeave);
            data.put(Constant.CONST_DATA, positionLeave);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HashMap<String, Object>> deleteData(@RequestParam Long positionLeaveId) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            PositionLeave positionLeave = positionLeaveServices.getDataById(positionLeaveId);
            positionLeaveServices.deleteData(positionLeave);
            data.put(Constant.CONST_DATA, positionLeave);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
