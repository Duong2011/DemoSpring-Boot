package De.com.example.demoAPIJAVA.Repository;

import De.com.example.demoAPIJAVA.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByName(String name);
}
