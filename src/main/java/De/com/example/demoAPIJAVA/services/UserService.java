package De.com.example.demoAPIJAVA.services;

import De.com.example.demoAPIJAVA.Repository.UserInfoRepository;
import De.com.example.demoAPIJAVA.entity.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public record UserService(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
    public String addUser(UserInfo userInfo)
    {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "add user successfully";
    }
}
