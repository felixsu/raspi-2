package com.felix.raspi.service;

import com.felix.raspi.model.entity.Users;
import com.felix.raspi.repository.UserRepository;
import com.felix.raspi.service.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fsoewito on 2/20/2016.
 */

@Service
public class UserService extends GenericService<Users, Integer> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
