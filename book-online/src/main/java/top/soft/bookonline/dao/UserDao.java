package top.soft.bookonline.dao;

import top.soft.bookonline.entity.User;

public interface UserDao {
    /**
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     *
     * @param user
     * @return
     */

    User findUser(User user);
}
