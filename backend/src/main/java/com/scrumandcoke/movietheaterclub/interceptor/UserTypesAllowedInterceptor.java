package com.scrumandcoke.movietheaterclub.interceptor;

import com.scrumandcoke.movietheaterclub.annotation.LoginRequired;
import com.scrumandcoke.movietheaterclub.annotation.UserTypesAllowed;
import com.scrumandcoke.movietheaterclub.dto.UserSessionDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
public class UserTypesAllowedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();
        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        UserTypesAllowed userTypesAllowed = method.getAnnotation(UserTypesAllowed.class);

        if (Objects.isNull(userTypesAllowed)) {
            return true;
        }

        if (Objects.isNull(loginRequired)) {
            throw new RuntimeException("Improperly configured annotations");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        UserSessionDetail userDetails = (UserSessionDetail) authentication.getPrincipal();
        if (Arrays.stream(userTypesAllowed.value()).anyMatch(type -> type.equals(userDetails.getUserType()))) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}

