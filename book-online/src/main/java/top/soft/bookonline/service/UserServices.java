package top.soft.bookonline.service;

import top.soft.bookonline.entity.User;

public interface UserServices {
    /**
     *
     * @param account
     * @param password
     * @return
     */
    User signIn(String account,String password);

    boolean isUsernameExists(String account);

    void register(User user);
}
