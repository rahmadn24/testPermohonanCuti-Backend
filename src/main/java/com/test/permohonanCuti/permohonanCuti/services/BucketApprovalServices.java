package com.test.permohonanCuti.permohonanCuti.services;

import com.test.permohonanCuti.permohonanCuti.model.BucketApproval;
import com.test.permohonanCuti.permohonanCuti.repository.BucketApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketApprovalServices {

    @Autowired
    BucketApprovalRepository bucketApprovalRepository;

    public void inputData(BucketApproval bucketApproval) {
        bucketApprovalRepository.save(bucketApproval);
    }

    public List<BucketApproval> getDataAll() {
        return bucketApprovalRepository.findAll();
    }

//    public List<BucketApproval> getDataAllById(Long userId) {
//        return bucketApprovalRepository.findByUserId(userId);
//    }

    public BucketApproval getDataById(Long userId) {
        Optional<BucketApproval> opt = bucketApprovalRepository.findById(userId);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public void deleteData(BucketApproval bucketApproval) {
        bucketApprovalRepository.delete(bucketApproval);
    }
}
