package com.test.permohonanCuti.permohonanCuti.controller;

import com.test.permohonanCuti.permohonanCuti.dto.PositionDto;
import com.test.permohonanCuti.permohonanCuti.model.Position;
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
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionServices positionServices;

    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger  = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getData() {
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<Position> list = positionServices.getDataAll();
            data.put(Constant.CONST_DATA, list);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{positionName}")
    public ResponseEntity<HashMap<String, Object>> getDataById(@PathVariable(value="positionName") String positionName) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            Position list = positionServices.getDataByPositionName(positionName);
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
    public ResponseEntity<HashMap<String, Object>> insertData(@RequestBody PositionDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        Position position = modelMapper.map(dto, Position.class);
        try {
            positionServices.inputData(position);
            data.put(Constant.CONST_DATA, position);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> updateData(@RequestBody PositionDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            Position position = positionServices.getDataByPositionName(dto.getPositionName());
            position.setPositionName(dto.getPositionName() != null ? dto.getPositionName() : position.getPositionName());
            position.setLevel(dto.getLevel() != null ? dto.getLevel() : position.getLevel());
            position.setUpdateBy(dto.getUpdateBy() != null ? dto.getUpdateBy() : position.getUpdateBy());
            position.setUpdateDate(new Date());
            positionServices.inputData(position);
            data.put(Constant.CONST_DATA, position);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HashMap<String, Object>> deleteData(@RequestParam String positionName) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            Position position = positionServices.getDataByPositionName(positionName);
            positionServices.deleteData(position);
            data.put(Constant.CONST_DATA, position);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
