package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tweets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote", referencedColumnName = "id", nullable = true)
    private Tweet quotedTweet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refer", referencedColumnName = "id", nullable = true)
    private Tweet referredTweet;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false, length = 280)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @Builder
    public Tweet(User user, String content) {
        this.user = user;
        this.content = content;
    }

    // 논리 삭제
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
    // 삭제 확인
    public boolean isDeleted() { return this.deletedAt != null; }
}
