package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.repository.UserDb;
import apap.ta.sipelatihan.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDB;

    private final WebClient webClient;

    public UserServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.APIsipegawai).build();
    }

    @Override
    public boolean checkPassword(String password) {
        boolean atleastOneAlpha = password.matches(".*[a-zA-Z]+.*");
        boolean atleastOneNumber = password.matches(".*[0-9]+.*");
        boolean atleastEightChar = password.length() >= 8;
        return atleastOneAlpha && atleastOneNumber && atleastEightChar;
    }

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);

        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        String hashedPassword = new BCryptPasswordEncoder().encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findUser(String user) {
        return userDB.findByUsername(user);
    }

    @Override
    public UserModel findUserById(String id) {
        return userDB.findByUuid(id);
    }
}
