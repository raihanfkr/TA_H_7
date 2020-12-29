package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.UserModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel findUser(String user);
    boolean checkPassword(String password);
    UserModel findUserById(String id);
}

