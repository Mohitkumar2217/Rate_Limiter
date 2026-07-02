package model;


public class RateLimitConfig {
    public final int maxRequest;
    public final int windowInSecond;
    
    public RateLimitConfig(int maxRequest, int windowInSecond) {
        this.maxRequest = maxRequest;
        this.windowInSecond = windowInSecond;
    }

    public int getMaxRequests() {
        return maxRequest;
    }

    public int getWindowInSeconds() {
        return windowInSecond;
    }
}
