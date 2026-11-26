package abl.frd.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import abl.frd.service.AuthService;
import abl.frd.service.CommonService;

import java.util.*;

@RestController
@RequestMapping("/api/auth") 
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @RequestMapping(value="/login", method= RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestParam Map<String, String> formData, HttpServletRequest request){
        Map<String, Object> resp = new HashMap<>();
        String userId = formData.get("userId");
        String password = formData.get("password");
        resp = authService.getLogin(userId, password, request);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(value="/logout", method= RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("loginInfo");
            session.removeAttribute("login_info");
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        // Clear DEALSESSIONID cookie on the client
        Cookie cookie = new Cookie("DEALSESSIONID", "");
        cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        Map<String, Object> resp = CommonService.getResp(0, "Logged out successfully", null);
        return ResponseEntity.ok(resp);
    }
}
