package com.test.permohonanCuti.permohonanCuti.services;

import com.test.permohonanCuti.permohonanCuti.dto.UserLeaveView;
import com.test.permohonanCuti.permohonanCuti.model.UserLeaveRequest;
import com.test.permohonanCuti.permohonanCuti.repository.UserLeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserLeaveRequestService {
    @Autowired
    UserLeaveRequestRepository userLeaveRequestRepository;

    public void inputData(UserLeaveRequest userLeaveRequest) {
        userLeaveRequestRepository.save(userLeaveRequest);
    }

    public List<UserLeaveRequest> getDataAll() {
        return userLeaveRequestRepository.findAll();
    }

    public List<UserLeaveView> getDataAllByUserId(Long user, Pageable pageable) {
        System.out.println(user);
        System.out.println(pageable);
        return userLeaveRequestRepository.findByByUsers(user, pageable);
    }

    public UserLeaveRequest getDataById(Long userId) {
        Optional<UserLeaveRequest> opt = userLeaveRequestRepository.findById(userId);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public void deleteData(UserLeaveRequest userLeaveRequest) {
        userLeaveRequestRepository.delete(userLeaveRequest);
    }
}
