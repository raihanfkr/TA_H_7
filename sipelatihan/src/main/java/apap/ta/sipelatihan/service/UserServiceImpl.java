package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findUser(String username) {
        return userDB.findByUsername(username);
    }

    @Override
    public boolean checkPassword(String password) {
        boolean atleastOneAlpha = password.matches(".*[a-zA-Z]+.*");
        boolean atleastOneNumber = password.matches(".*[0-9]+.*");
        boolean atleastEightChar = password.length() >= 8;
        return atleastOneAlpha && atleastOneNumber && atleastEightChar;
    }
}