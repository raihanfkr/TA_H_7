package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);

    public String encrypt(String password);

    UserModel findUser(String username);

    boolean checkPassword(String password);
}