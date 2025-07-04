package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(nullable = false, length = 50, unique = true)
    private String userId;

    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45)
    private String ipCreate;

    @Column(nullable = false, length = 45)
    private String ipUpdate;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @Builder
    public User(String userId, String email, String phoneNumber, String password, String ipCreate, String ipUpdate) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.ipCreate = ipCreate;
        this.ipUpdate = ipUpdate;
    }

    // 논리 삭제
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
    // 삭제 취소
    public void cancelDelete() {
        this.deletedAt = null;
    }
    // 삭제 확인
    public boolean isDeleted() { return this.deletedAt != null; }
}
