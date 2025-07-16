package moe.overnight.clontwitter.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String userId,
        String email
) {}
