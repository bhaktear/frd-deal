package abl.frd.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.*;;

@Service
@SuppressWarnings("unchecked")
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final SecondaryQueryService secondaryQueryService;
    private String msg = "Invalid User Id or Password";
    @Autowired 
    MenuService menuService;
    public AuthService(AuthenticationManager authenticationManager, SecondaryQueryService secondaryQueryService) {
        this.authenticationManager = authenticationManager;
        this.secondaryQueryService = secondaryQueryService;
    }
    
    public Map<String, Object> getLogin(String userId, String password, HttpServletRequest request) {
        Map<String, Object> resp = new HashMap<>();

        if(CommonService.checkEmptyString(userId) || CommonService.checkEmptyString(password)){
            return CommonService.getResp(1,this.msg, null);
        }
        userId = userId.trim();
        password = password.trim();
        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId,password)
            );
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
            resp = buildSanitizedLoginInfo(userId, auth.getName());
            session.setAttribute("loginInfo", resp.get("data"));
        }catch(AuthenticationException e){
            return CommonService.getResp(1,this.msg, null);
        }
        catch(Exception e){
            e.printStackTrace();
            return CommonService.getResp(1,this.msg, null);
        }
        return resp;
    }

    private Map<String, Object> buildSanitizedLoginInfo(String userId, String fallbackLoginId){
        Map<String, Object> resp = new HashMap<>();
        try{
            Map<String, Object> userResp = secondaryQueryService.getUser(userId);
            int err = (int) userResp.get("err");
            if(err == 1)    return userResp;
            if(userResp != null && err == 0){
                List<Object> dataList = (List<Object>) userResp.get("data");
                Map<String, Object> data = new HashMap<>();
                resp.put("login_id", fallbackLoginId);
                if(!dataList.isEmpty() && dataList.get(0) != null){
                    data = (Map<String, Object>) dataList.get(0);
                    data.remove("password");
                }
                resp.put("err", err);
                resp.put("msg", "Login Successful");
                resp.put("data", data);
            }
        }catch(Exception e){
            e.printStackTrace();
            return CommonService.getResp(1, this.msg, null);
        }
        return resp;
    }

}
