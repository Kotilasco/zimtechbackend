package com.zimttech.diabeticscreening.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),

    ADMIN_CREATE("admin:create"),

    ADMIN_DELETE("admin:delete"),
    PATIENT_READ("patient:read"),

    ;

    @Getter
    private final String permission;
}

