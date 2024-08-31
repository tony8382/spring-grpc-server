package com.lyyang.test.testgrpc.security;


import com.lyyang.test.testgrpc.JwtClaim;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtClaimsService {

    public Optional<String> getAppId() {
        return getClaim(JwtClaim.app_id.name());
    }

    public Optional<String> getPermissionId() {
        return getClaim(JwtClaim.permission_id.name());
    }

    private Optional<String> getClaim(String claimName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return Optional.ofNullable(jwt.getClaimAsString(claimName));
        }
        return Optional.empty();
    }
}