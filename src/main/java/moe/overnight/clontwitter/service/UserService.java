package moe.overnight.clontwitter.service;

import lombok.RequiredArgsConstructor;
import moe.overnight.clontwitter.dto.UserResponse;
import moe.overnight.clontwitter.dto.UserSignupRequest;
import moe.overnight.clontwitter.model.User;
import moe.overnight.clontwitter.model.UserInform;
import moe.overnight.clontwitter.model.UserStatistics;
import moe.overnight.clontwitter.repository.UserInformRepository;
import moe.overnight.clontwitter.repository.UserRepository;
import moe.overnight.clontwitter.repository.UserStatisticsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInformRepository userInformRepository;
    private final UserStatisticsRepository userStatisticsRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입 로직
    public UUID signup(UserSignupRequest request) {
        // 1. 중복 검사
        //      이메일 체크
        if(userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }
        //      userId 체크
        if(userRepository.existsByUserId(request.userId())) {
            throw new IllegalArgumentException("이미 존재하는 유저 ID");
        }
        //      전화번호 체크
        if(userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호");
        }

        // 2. 엔티티 생성
        //      주 엔티티 - user 엔티티
        User user = User.builder()
                .userId(request.userId())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .ipCreate("127.0.0.1")
                .ipUpdate("127.0.0.1")
                .build();
        userRepository.save(user);

        //      연관 엔티티 - 유저 정보 엔티티
        userInformRepository.save(UserInform.builder()
                        .user(user)
                        .nickname("")
                        .introduce(null)
                        .headerUrl(null)
                        .profileUrl(null)
                        .build());

        //      연관 엔티티 - 유저 통계 엔티티
        userStatisticsRepository.save(UserStatistics.builder()
                        .user(user)
                .build());

        return user.getId();
    }

    // 유저 조회 로직
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("사용자 없음"));

        return new UserResponse(user.getId(), user.getUserId(), user.getEmail());
    }
}
