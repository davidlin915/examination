package cn.jy.dao;

import cn.jy.domain.User;

public interface UserDao {

    User findUserByemployee_idAndPassword(String employee_id, String password);

    User findurl_addressByemployee_id(String i);

    void update(User user);
}
