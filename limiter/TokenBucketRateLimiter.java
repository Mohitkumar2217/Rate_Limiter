package limiter;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import enums.RateLimitType;
import model.RateLimitConfig;

public class TokenBucketRateLimiter extends RateLimiter {
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefilltime = new HashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.TOKEN_BUCKET);
    }

    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(userId, now);

            if(currentTokens > 0) {
                allowed.set(true);
                return currentTokens - 1;
            } else {
                return currentTokens;
            }
        });
        return allowed.get();
    }

    private int refillTokens(String userId, long now) {
        double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();

        long lastRefill = lastRefilltime.getOrDefault(userId, now);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedSeconds / refillRate);

        int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
        if(refillTokens > 0) lastRefilltime.put(userId, now);
        
        return currentTokens;
    }
}
