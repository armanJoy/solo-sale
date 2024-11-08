package com.test.inventory.config.authentication.service;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.config.authentication.jwt.JwtHelper;
import com.test.inventory.config.authentication.model.RefreshToken;
import com.test.inventory.config.authentication.repository.RefreshTokenRepository;
import com.test.inventory.dto.request.user.RefreshTokenRequest;
import com.test.inventory.dto.response.user.RefreshTokenResponse;
import com.test.inventory.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtHelper jwtHelper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    @Value("${jwt.refresh-time}")
    private Long refreshTokenExpireTime;

    @Autowired
    public RefreshTokenServiceImpl(JwtHelper jwtHelper, RefreshTokenRepository refreshTokenRepository,
                                   UserService userService) {
        this.jwtHelper = jwtHelper;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }


    @Override
    public RefreshToken createRefreshToken(Long userId) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userId);
        RefreshToken refreshToken;
        refreshToken = optionalRefreshToken.orElseGet(RefreshToken::new);
        refreshToken.setExpireTime(Instant.now().plusMillis(refreshTokenExpireTime));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(userService.findByUserId(userId));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyRefreshTokenExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpireTime().compareTo(Instant.now()) < ApplicationConstant.NUMBERS.ZERO) {
            refreshTokenRepository.delete(refreshToken);
            throw new CommonServerException("Refresh token expired", HttpStatus.BAD_REQUEST);
        }
        return refreshToken;
    }

    @Override
    public RefreshTokenResponse tokenRefresh(RefreshTokenRequest request) {
        Optional<RefreshToken> exRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        return exRefreshToken
                .map(this::verifyRefreshTokenExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwt = jwtHelper.generateToken(user.getUsername());
                    return new RefreshTokenResponse(jwt, request.getRefreshToken(), ApplicationConstant.AUTHORIZATION_TYPE_BEARER);
                }).orElseThrow(() -> new CommonServerException("Couldn't refresh token", HttpStatus.BAD_REQUEST));
    }


}