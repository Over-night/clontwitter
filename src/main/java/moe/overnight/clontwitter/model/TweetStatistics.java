package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "tweet_statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TweetStatistics {
    @Id
    @Column
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Tweet tweet;

    @Column(nullable = false)
    private int retweets = 0;

    @Column(nullable = false)
    private int hearts = 0;

    @Column(nullable = false)
    private int views = 0;

    @Builder
    public TweetStatistics(Tweet tweet) {
        this.tweet = tweet;
    }

    public void increaseRetweets() { this.retweets++; }
    public void decreaseRetweets() { this.retweets--; }
    public void increaseHearts() { this.hearts++; }
    public void decreaseHearts() { this.hearts--; }
    public void increaseViews() { this.views++; }
}
