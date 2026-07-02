package model;

import enums.UserTier;

public class User {
    public final String userId;
    public final UserTier tier;

    public User(String userId, UserTier tier) {
        this.tier = tier;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public UserTier getTier() {
        return tier;
    }
}
