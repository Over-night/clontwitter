package moe.overnight.clontwitter.dto;

public record UserSignupRequest(
        String userId,
        String email,
        String phoneNumber,
        String password
) {}
