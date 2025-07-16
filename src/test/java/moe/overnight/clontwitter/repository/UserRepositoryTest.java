package moe.overnight.clontwitter.repository;

import moe.overnight.clontwitter.config.TestSecurityConfig;
import moe.overnight.clontwitter.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestSecurityConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User createUser() {
        return User.builder()
                .userId("test_01")
                .email("test@example.com")
                .phoneNumber("+821000000000")
                .password(passwordEncoder.encode("password")) // encode "password" by SHA256
                .ipCreate("127.0.0.1")
                .ipUpdate("127.0.0.1")
                .build();
    }


    // 유저 Id로 사용자 조회
    @DisplayName("조회 : userId 기준")
    @Test
    void searchUserByUserId() {
        // given
        User user = createUser();
        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findByUserId("test_01");

        // then
        assertTrue(result.isPresent());
        assertEquals(user.getUserId(), result.get().getUserId());
    }

    // 이메일로 사용자 조회
    @DisplayName("조회 : email 기준")
    @Test
    void searchUserByEmail() {
        // given
        User user = createUser();
        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findByEmail("test@example.com");

        // then
        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

    // 전화번호로 사용자 조회
    @DisplayName("조회 : 전화번호 기준")
    @Test
    void searchUserByPhoneNumber() {
        // given
        User user = createUser();
        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findByPhoneNumber("+821000000000");

        // then
        assertTrue(result.isPresent());
        assertEquals(user.getPhoneNumber(), result.get().getPhoneNumber());
    }

    // 비밀번호 암호화 체크
    @DisplayName("검증 : password 암호화")
    @Test
    void passwordValidCheck() {
        // given
        User user = createUser();
        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findById(user.getId());

        // then
        assertTrue(result.isPresent());

        assertNotEquals("password", result.get().getPassword());
        assertTrue(passwordEncoder.matches("password", result.get().getPassword()));
    }
}
