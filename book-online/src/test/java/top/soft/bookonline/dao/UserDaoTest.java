package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.UserDaoimpl;
import top.soft.bookonline.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void insertUser() {
        UserDao userDao = new UserDaoimpl();
        User user = User.builder().account("ssw").nickname("ssw").password("jiangsuren779").address("江苏南京").avatar("C:/Users/24399/Desktop/xixi.jpg").build();
        userDao.insertUser(user);
    }
}