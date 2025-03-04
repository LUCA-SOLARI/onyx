package com.lucasolari.portfolio.onyx.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public boolean sessionExists(HttpSession session){
        return session!=null;
    }

    public HttpSession createSession(HttpServletRequest request){
        return request.getSession(true);
    }

    public void invalidateSession(HttpSession session){
        session.invalidate();
    }
}
