package service;

import java.util.HashMap;
import java.util.Map;

import enums.RateLimitType;
import enums.UserTier;
import factory.RateLimiterFactory;
import limiter.RateLimiter;
import model.RateLimitConfig;
import model.User;

public class RateLimiterService {
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {

        rateLimiters.put(
            UserTier.FREE,
            RateLimiterFactory.createRateLimiter(
                    RateLimitType.TOKEN_BUCKET,
                    new RateLimitConfig(10, 60)
            )
        );

        rateLimiters.put(
            UserTier.PREMIUM,
            RateLimiterFactory.createRateLimiter(
                RateLimitType.FIXED_WINDOW,
                new RateLimitConfig(100, 60)
            )
        );

        rateLimiters.put(
            UserTier.PREMIUMPLUS,
            RateLimiterFactory.createRateLimiter(
                RateLimitType.SLIDING_WINDOW_LOG,
                new RateLimitConfig(500, 60)
            )
        );
    }

    public boolean allowRequest(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        RateLimiter limiter = rateLimiters.get(user.getTier());

        if (limiter == null) {
            throw new IllegalArgumentException("No rate limiter configured for tier: " + user.getTier());
        }

        return limiter.allowRequest(user.getUserId());
    }
}