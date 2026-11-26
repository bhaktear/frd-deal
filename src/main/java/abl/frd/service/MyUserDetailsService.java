package abl.frd.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import abl.frd.helper.MyUserDetails;
import java.util.*;
@Service 
@SuppressWarnings("unchecked")
public class MyUserDetailsService implements UserDetailsService{
    @Autowired 
    SecondaryQueryService secondaryQueryService;
    
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> resp = secondaryQueryService.getUser(username);
        if (resp == null || !Integer.valueOf(0).equals(resp.get("err")) || !(resp.get("data") instanceof List)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<Map<String,Object>> rows = (List<Map<String, Object>>) resp.get("data");
        if (rows.isEmpty() || rows.get(0) == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Map<String, Object> row = rows.get(0);
        System.out.println(row);
        String loginId = CommonService.convertObjToString(row.get("login_id"));
        String password = CommonService.convertObjToString(row.get("password"));
        String userId = CommonService.convertObjToString(row.get("user_id"));
        String userEmail = CommonService.convertObjToString(row.get("user_email"));
        String userName = CommonService.convertObjToString(row.get("user_name"));
        String mobileNo = CommonService.convertObjToString(row.get("mobile"));
        String allowedIps = CommonService.convertObjToString(row.get("allowed_ips"));
        String startTime = CommonService.convertObjToString(row.get("start_time"));
        String endTime = CommonService.convertObjToString(row.get("end_time"));
        Integer failedAttempt = CommonService.convertObjToInteger(row.get("failed_attempt"));
        String exchangeCode = CommonService.convertObjToString(row.get("exchange_code"));
        Boolean activeStatus = (Boolean) row.get("active_status");
        Boolean passwordChangeRequired = (Boolean) row.get("password_change_required");
        String roleId = CommonService.convertObjToString(row.get("role_id"));
        String roleName = CommonService.convertObjToString(row.get("role_name"));
        String authorityName = roleName.trim();
        boolean enabled = activeStatus != null ? activeStatus : true;

        return new MyUserDetails(username, password, enabled, Collections.singletonList(new SimpleGrantedAuthority(authorityName)), loginId, userEmail, allowedIps, 
            userName, mobileNo, endTime, failedAttempt, startTime, activeStatus, userId, roleId, passwordChangeRequired, exchangeCode, row);
    }
}
