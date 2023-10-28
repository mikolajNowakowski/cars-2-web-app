package com.app.service.authorization;


import com.app.service.authorization.security.AuthorizationRole;

public interface AuthorizationCheckService {


    boolean authorize(String accessToken,String uri);
}
