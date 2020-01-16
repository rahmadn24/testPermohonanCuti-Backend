package com.test.permohonanCuti.permohonanCuti.services;

import com.test.permohonanCuti.permohonanCuti.model.User;
import com.test.permohonanCuti.permohonanCuti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public void inputData(User user) {
        userRepository.save(user);
    }

    public List<User> getDataAll() {
        return userRepository.findAll();
    }

    public List<User> getDataAllById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public User getDataById(Long userId) {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
    }

    public void deleteData(User user) {
        userRepository.delete(user);
    }
}
