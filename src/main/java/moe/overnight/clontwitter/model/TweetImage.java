package moe.overnight.clontwitter.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "tweet_image",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tweet_id", "imageUrl"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TweetImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @Column(nullable = true, length = 2048)
    private String imageUrl;

    @Builder
    public TweetImage(Tweet tweet, String imageUrl) {
        this.tweet = tweet;
        this.imageUrl = imageUrl;
    }
}
