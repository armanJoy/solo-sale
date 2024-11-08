package com.test.inventory.config.authentication.service;

import com.test.inventory.config.authentication.model.RefreshToken;
import com.test.inventory.dto.request.user.RefreshTokenRequest;
import com.test.inventory.dto.response.user.RefreshTokenResponse;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyRefreshTokenExpiration(RefreshToken refreshToken);

    RefreshTokenResponse tokenRefresh(RefreshTokenRequest request);
}