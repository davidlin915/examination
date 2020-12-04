package cn.jy.dao.impl;

import cn.jy.dao.UserDao;
import cn.jy.domain.User;
import cn.jy.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByemployee_idAndPassword(String employee_id, String password) {
        try {
            String sql = "select * from user where employee_id = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), employee_id, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findurl_addressByemployee_id(String id) {
        String sql = "select * from user where employee_id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "update user set url_address = ? where employee_id = ?";
        template.update(sql, user.getUrl_address(), user.getEmployee_id());
    }
}
