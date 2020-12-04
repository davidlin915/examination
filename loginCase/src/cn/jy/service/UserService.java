package cn.jy.service;

import cn.jy.domain.User;

public interface UserService {

    User login(User user);

    User findurl_addressByemployee_id(String id);

    void updateUser(User user);
}
