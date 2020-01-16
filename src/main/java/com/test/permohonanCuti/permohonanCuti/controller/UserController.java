package com.test.permohonanCuti.permohonanCuti.controller;

import com.test.permohonanCuti.permohonanCuti.dto.UserDto;
import com.test.permohonanCuti.permohonanCuti.model.Position;
import com.test.permohonanCuti.permohonanCuti.model.PositionLeave;
import com.test.permohonanCuti.permohonanCuti.model.User;
import com.test.permohonanCuti.permohonanCuti.services.PositionLeaveServices;
import com.test.permohonanCuti.permohonanCuti.services.PositionServices;
import com.test.permohonanCuti.permohonanCuti.services.UserServices;
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
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    PositionServices positionServices;

    @Autowired
    PositionLeaveServices positionLeaveServices;

    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger  = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getData() {
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<User> list = userServices.getDataAll();
            data.put(Constant.CONST_DATA, list);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<HashMap<String, Object>> getDataById(@PathVariable(value="userId") Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<User> list = userServices.getDataAllById(userId);
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
    public ResponseEntity<HashMap<String, Object>> insertData(@RequestBody UserDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        User user = modelMapper.map(dto, User.class);
        try {
            dto.setPositionId(dto.getPositionName() != null ? dto.getPositionName() : "employee");
            PositionLeave positionLeave = positionLeaveServices.getDataByPosition(positionServices.getDataByPositionName(dto.getPositionName()));
            user.setPosition(positionServices.getDataByPositionName(dto.getPositionName()));
            user.setLeaveAvaible(positionLeave.getLeaveApprove());
            userServices.inputData(user);
            data.put(Constant.CONST_DATA, user);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HashMap<String, Object>> updateData(@RequestBody UserDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            User user = userServices.getDataById(dto.getUserId());
            user.setUserName(dto.getUserName() != null ? dto.getUserName() : user.getUserName());
            user.setPosition(dto.getPositionName() != null ? positionServices.getDataByPositionName(dto.getPositionName()) : user.getPosition());
            user.setUpdateBy(dto.getUpdateBy() != null ? dto.getUpdateBy() : user.getUpdateBy());
            user.setUpdateDate(new Date());
            userServices.inputData(user);
            data.put(Constant.CONST_DATA, user);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HashMap<String, Object>> deleteData(@RequestParam Long id) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            User user = userServices.getDataById(id);
            userServices.deleteData(user);
            data.put(Constant.CONST_DATA, user);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
