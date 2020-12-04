package cn.jy.service.impl;

import cn.jy.dao.UserDao;
import cn.jy.dao.impl.UserDaoImpl;
import cn.jy.domain.User;
import cn.jy.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.findUserByemployee_idAndPassword(user.getEmployee_id(), user.getPassword());
    }

    @Override
    public User findurl_addressByemployee_id(String id) {
        return dao.findurl_addressByemployee_id(id);
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

}
