package com.youzhong.service;

import com.youzhong.entity.User;

public interface UserService {
    Integer register(User user);

    User selectMes(User user);

    User selectuid(String id);
}
