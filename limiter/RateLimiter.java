package limiter;

import enums.RateLimitType;
import model.RateLimitConfig;

public abstract class RateLimiter {
    protected RateLimitConfig config;
    protected RateLimitType type;

    public RateLimiter(RateLimitConfig config, RateLimitType type) {
        this.config = config;
        this.type = type;
    } 
    public abstract boolean allowRequest(String userId);
}
