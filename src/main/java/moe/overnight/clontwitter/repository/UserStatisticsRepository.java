package moe.overnight.clontwitter.repository;

import moe.overnight.clontwitter.model.User;
import moe.overnight.clontwitter.model.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserStatisticsRepository extends JpaRepository<UserStatistics, UUID> {
    Optional<UserStatistics> findByUser(User user);
}