package com.jjurisic.android.movielist.api.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RequestInterceptor;

@Singleton
public class ApiHeaders implements RequestInterceptor {

    private static final String AUTHORIZATION_PREFIX = "Auth: ";

    private final String authorizationValue;

    @Inject
    public ApiHeaders(@ClientId String clientId) {
        authorizationValue = AUTHORIZATION_PREFIX + clientId;
    }

    public String getAuthorizationHeader() {
        return authorizationValue;
    }

    public String getAuthorizationPrefix() {
        return AUTHORIZATION_PREFIX;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", authorizationValue);
    }
}