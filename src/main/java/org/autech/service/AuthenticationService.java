package org.autech.service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.autech.Exception.OMSException;
import org.autech.model.ApiKeyAuthentication;
import org.autech.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {

    private static final String readRoleName = "read";
    private static final String writeRoleName = "write";
    private static final String adminRoleName = "admin";

    @Value("${api.token.headername}")
    private String apiTokenHeaderName;

    @Autowired
    private UserService userService;

    private final Map<String, User> apiKeyToUserId = new HashMap<>();

    @PostConstruct
    public void populateCache(){
        apiKeyToUserId.putAll(userService.getApiKeyToUserMap());
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKeyFromRequest = request.getHeader(apiTokenHeaderName);
        if (apiKeyFromRequest == null || !apiKeyToUserId.containsKey(apiKeyFromRequest)) {
            throw new OMSException("Invalid API Key or no API Key provided");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(apiKeyToUserId.get(apiKeyFromRequest).getUserGroups());
        request.setAttribute("userId", apiKeyToUserId.get(apiKeyFromRequest).getUserId());
        return new ApiKeyAuthentication(apiKeyFromRequest, grantedAuthorities);
    }

    public boolean checkDataReadAccess(String userId){
        User user = userService.getUserById(userId);
        return checkAdminAccess(userId) || checkWriteAccess(userId) || user.getUserGroups().contains(readRoleName);
    }

    public boolean checkWriteAccess(String userId){
        User user = userService.getUserById(userId);
        return checkAdminAccess(userId) || user.getUserGroups().contains(writeRoleName);
    }

    public boolean checkAdminAccess(String userId){
        User user = userService.getUserById(userId);
        return user.getUserGroups().contains(adminRoleName);
    }
}
