package moe.overnight.clontwitter.repository;

import moe.overnight.clontwitter.model.Tweet;
import moe.overnight.clontwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Optional<Tweet> findById(Long id);
    Optional<Tweet> findByUserId(User user);

}