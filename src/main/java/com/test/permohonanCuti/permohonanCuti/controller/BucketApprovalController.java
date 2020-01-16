package com.test.permohonanCuti.permohonanCuti.controller;

import com.test.permohonanCuti.permohonanCuti.dto.BucketApprovalDto;
import com.test.permohonanCuti.permohonanCuti.model.BucketApproval;
import com.test.permohonanCuti.permohonanCuti.model.User;
import com.test.permohonanCuti.permohonanCuti.model.UserLeaveRequest;
import com.test.permohonanCuti.permohonanCuti.services.BucketApprovalServices;
import com.test.permohonanCuti.permohonanCuti.services.UserLeaveRequestService;
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

@RestController
@RequestMapping("/resolveRequestLeave")
public class BucketApprovalController {

    @Autowired
    BucketApprovalServices bucketApprovalServices;

    @Autowired
    UserLeaveRequestService userLeaveRequestService;

    @Autowired
    UserServices userServices;

    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger  = LoggerFactory.getLogger(BucketApprovalController.class);

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> insertData(@RequestBody BucketApprovalDto dto) {
        HashMap<String, Object> data = new HashMap<>();
        try {
            BucketApproval bucketApproval1 = bucketApprovalServices.getDataById(dto.getBucketApprovalId());
            UserLeaveRequest userLeaveRequest = userLeaveRequestService.getDataById(bucketApproval1.getUserLeaveRequest().getUserLeaveRequestId());
            bucketApproval1.setResolverReason(dto.getResolverReason());
            bucketApproval1.setResolvedDate(dto.getRequestDate());
            bucketApproval1.setResolvedBy(dto.getResolvedBy());
            bucketApproval1.setUpdateBy(dto.getResolvedBy());
            bucketApproval1.setUpdateDate(dto.getResolvedDate());
            userLeaveRequest.setStatus(dto.getStatus());
            long selisihMS = Math.abs(userLeaveRequest.getLeaveDateFrom().getTime() - userLeaveRequest.getLeaveDateTo().getTime());
            long jumlahCuti = (selisihMS / (24 * 60 * 60 * 1000)) + 1;
            if (bucketApprovalServices.getDataById(dto.getBucketApprovalId()) == null){
                data.put(Constant.CONST_DATA, "Permohonan dengan ID " + dto.getBucketApprovalId() + " tidak ditemukan.");
                data.put(Constant.CONST_STATUS, HttpStatus.OK);
            }else if (dto.getResolvedDate().compareTo(userLeaveRequest.getLeaveDateFrom()) > 0) {
                data.put(Constant.CONST_DATA, "Kesalahan data, tanggal keputusan tidak bisa lebih awal dari pengajuan cuti.");
                data.put(Constant.CONST_STATUS, HttpStatus.OK);
            }else{
                bucketApprovalServices.inputData(bucketApproval1);
                userLeaveRequestService.inputData(userLeaveRequest);
                if (dto.getStatus().equals("approved")){
                    User user = userServices.getDataById(userLeaveRequest.getUser().getUserId());
                    user.setLeaveAvaible((int) (user.getLeaveAvaible() - jumlahCuti));
                    userServices.inputData(user);
                }
                    data.put(Constant.CONST_DATA, "Permohonan dengan ID " + dto.getBucketApprovalId() + " telah berhasil diputuskan.");
                    data.put(Constant.CONST_STATUS, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(String.format(Constant.CONST_ERROR, e.getMessage()));
            data.put(Constant.CONST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            data.put(Constant.CONST_MSG, e.getMessage());
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
