package org.autech.service;

import org.autech.model.User;
import org.autech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(String userId){
        return userRepository.findById(Integer.parseInt(userId)).orElse(null);
    }

    public Map<String, User> getApiKeyToUserMap(){
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toMap(User::getUserApiKey, Function.identity()));
    }
}
