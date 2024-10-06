package com.swiggyapp1.swiggyApp1.registration.constant;

public enum UserRole {
//    1. "End User"
//    2. "Delivery User"),
//    3. "Restaurant User");
    ONE("1", "End User"),
    TWO("2", "Delivery User"),
    THREE("3", "Restaurant User");

    private final String code;
    private final String displayName;

    UserRole(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Static method to get UserRole by code
    public static UserRole fromCode(int code) {
        for (UserRole role : UserRole.values()) {
            if (role.getCode().equals(code) ) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

