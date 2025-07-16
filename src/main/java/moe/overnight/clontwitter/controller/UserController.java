package moe.overnight.clontwitter.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import moe.overnight.clontwitter.dto.UserResponse;
import moe.overnight.clontwitter.dto.UserSignupRequest;
import moe.overnight.clontwitter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<UUID> signup(@Valid @RequestBody UserSignupRequest request) {
        UUID userId = userService.signup(request);
        return ResponseEntity.ok(userId);
    }
}
