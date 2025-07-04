package moe.overnight.clontwitter.repository;

import moe.overnight.clontwitter.model.User;
import moe.overnight.clontwitter.model.UserInform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserInformRepository extends JpaRepository<UserInform, UUID> {
    Optional<UserInform> findByUser(User user);
}