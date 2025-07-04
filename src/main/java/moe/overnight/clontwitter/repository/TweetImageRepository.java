package moe.overnight.clontwitter.repository;

import moe.overnight.clontwitter.model.Tweet;
import moe.overnight.clontwitter.model.TweetImage;
import moe.overnight.clontwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TweetImageRepository extends JpaRepository<TweetImage, Long> {
    Optional<TweetImage> findById(Long id);
    Optional<TweetImage> findByTweet(Tweet tweet);

}