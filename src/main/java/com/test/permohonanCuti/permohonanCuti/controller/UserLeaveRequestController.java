package com.test.permohonanCuti.permohonanCuti.controller;

import com.test.permohonanCuti.permohonanCuti.dto.UserLeaveRequestDto;
import com.test.permohonanCuti.permohonanCuti.dto.UserLeaveView;
import com.test.permohonanCuti.permohonanCuti.model.*;
import com.test.permohonanCuti.permohonanCuti.services.BucketApprovalServices;
import com.test.permohonanCuti.permohonanCuti.services.PositionLeaveServices;
import com.test.permohonanCuti.permohonanCuti.services.UserLeaveRequestService;
import com.test.permohonanCuti.permohonanCuti.services.UserServices;
import com.test.permohonanCuti.permohonanCuti.utils.Constant;
import org.hibernate.SharedSessionContract;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/requestLeave")
public class UserLeaveRequestController {

    @Autowired
    UserLeaveRequestService userLeaveRequestService;

    @Autowired
    BucketApprovalServices bucketApprovalServices;

    @Autowired
    UserServices userServices;

    @Autowired
    PositionLeaveServices positionLeaveServices;

    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger  = LoggerFactory.getLogger(UserLeaveRequest.class);

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> insertData(@RequestBody UserLeaveRequestDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        UserLeaveRequest userLeaveRequest = modelMapper.map(dto, UserLeaveRequest.class);
        BucketApproval bucketApproval = new BucketApproval();
        User user = userServices.getDataById(dto.getUser_id());
        long selisihMS = Math.abs(dto.getLeaveDateFrom().getTime() - dto.getLeaveDateTo().getTime());
        long jumlahCuti = (selisihMS / (24 * 60 * 60 * 1000)) + 1;
        try {
            if(now.compareTo(dto.getLeaveDateTo()) > 0 || now.compareTo(dto.getLeaveDateFrom()) > 0){
                data.put(Constant.CONST_STATUS, HttpStatus.OK);
                data.put(Constant.CONST_MSG, "Tanggal yang Anda ajukan telah lampau, silahkan ganti tanggal pengajuan cuti anda.");
            } else if (dto.getLeaveDateFrom().compareTo(dto.getLeaveDateTo()) > 0) {
                data.put(Constant.CONST_STATUS, HttpStatus.OK);
                data.put(Constant.CONST_MSG, "Tanggal yang Anda ajukan tidak valid.");
            } else {
                int avail = user.getLeaveAvaible();
                if(jumlahCuti > avail){
                    data.put(Constant.CONST_STATUS, HttpStatus.OK);
                    data.put(Constant.CONST_MSG, "Mohon maaf, jatah cuti Anda tidak cukup untuk digunakan dari "
                            + "tanggal " + dto.getLeaveDateFrom().toString() + "sampai " + dto.getLeaveDateTo().toString()
                            + " (" + jumlahCuti + " hari). Jatah cuti Anda yang tersisa adalah " + avail + " hari.");
                } else if(avail == 0){
                    data.put(Constant.CONST_STATUS, HttpStatus.OK);
                    data.put(Constant.CONST_MSG, "Mohon maaf, jatah cuti Anda telah habis.");
                } else{
                    userLeaveRequest.setStatus("waiting");
                    userLeaveRequest.setUser(userServices.getDataById(dto.getUser_id()));
                    userLeaveRequest.setCreatedDate(new Date());
                    userLeaveRequest.setCreatedBy("system");
                    userLeaveRequestService.inputData(userLeaveRequest);
                    bucketApproval.setUserLeaveRequest(userLeaveRequest);
                    bucketApproval.setCreatedDate(new Date());
                    bucketApproval.setRequestDate(new Date());
                    bucketApproval.setCreatedBy("system");
                    bucketApprovalServices.inputData(bucketApproval);
                    data.put(Constant.CONST_MSG, "Permohonan Anda sedang diproses.");
                    data.put(Constant.CONST_STATUS, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{totalDataPerPage}/{choosenPage}")
    public ResponseEntity<HashMap<String, Object>> getUserRequestList(@PathVariable Long userId,
                                                                      @PathVariable Integer totalDataPerPage,
                                                                      @PathVariable Integer choosenPage) {
        HashMap<String, Object> data = new HashMap<>();
        Pageable pageRequest = PageRequest.of(choosenPage, totalDataPerPage);
        List<Object> jsonObjectList = new ArrayList<>();
        try {
//            User user = userServices.getDataById(userId);
            List<UserLeaveView> userList = userLeaveRequestService.getDataAllByUserId(userId, pageRequest);
            List list = modelMapper.map(userList, (Type) UserLeaveView.class);
//            Object obj2 = userList.get(0);
//            ArrayList al1 = new ArrayList();
//            al1 = (ArrayList) obj2;
//            UserLeaveView userLeaveView = modelMapper.map(userList, UserLeaveView.class);
//            System.out.println(userList);
//            System.out.println(userLeaveView);
            for (UserLeaveView dc : userList) {
                HashMap<String, Object> data1 = new HashMap<>();
//                System.out.println("ini db"+dc.getLeaveDateFrom());
//                data1.put("userId", dc.getLeaveDateFrom());
//                data1.put("leaveDateFrom", dc.);
//                data1.put("leaveDateTo", dc.);
//                data1.put("description", dc.);
//                data1.put("status", dc.);
//                jsonObjectList.add(data1);
            }
            data.put(Constant.CONST_DATA, userList);
            data.put(Constant.CONST_STATUS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
