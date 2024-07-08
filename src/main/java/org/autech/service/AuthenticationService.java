package org.autech.service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.autech.model.ApiKeyAuthentication;
import org.autech.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {

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
            throw new BadCredentialsException("Invalid API Key");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(apiKeyToUserId.get(apiKeyFromRequest).getUserGroups());
        return new ApiKeyAuthentication(apiKeyFromRequest, grantedAuthorities);
    }
}
