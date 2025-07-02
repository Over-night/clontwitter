package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "user_inform")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserInform {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private User user;

    public void setUser(User user) { this.user = user; }

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = true, length = 280)
    private String introduce;

    @Column(nullable = true, length = 2048)
    private String headerUrl;

    @Column(nullable = true, length = 2048)
    private String profileUrl;

    @Builder
    public UserInform(String nickname, String introduce, String headerUrl, String profileUrl) {
        this.nickname = nickname;
        this.introduce = introduce;
        this.headerUrl = headerUrl;
        this.profileUrl = profileUrl;
    }
}
