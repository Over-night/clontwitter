package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "user_statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserStatistics {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int followers;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int followings;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int tweets;

    public void increaseFollowers() { this.followers++; }
    public void decreaseFollowers() { this.followers--; }
    public void increaseFollowings() { this.followings++; }
    public void decreaseFollowings() { this.followings--; }
    public void increaseTweets() { this.tweets++; }
}
