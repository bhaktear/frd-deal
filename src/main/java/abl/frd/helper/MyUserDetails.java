package abl.frd.helper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.*;

public class MyUserDetails extends User{
    private final String loginId;
    private final String userEmail;
    private final String allowedIps;
    private final String displayName;
    private final String mobileNo;
    private final String endTime;
    private final Integer failedAttempt;
    private final String startTime;
    private final Boolean activeStatus;
    private final String userId;
    private final String roleId;
    private final Boolean passwordChangeRequired;
    private final String exchangeCode;
    private final Map<String, Object> userData;

    public MyUserDetails(
            String username,
            String password,
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities,
            String loginId,
            String userEmail,
            String allowedIps,
            String displayName,
            String mobileNo,
            String endTime,
            Integer failedAttempt,
            String startTime,
            Boolean activeStatus,
            String userId,
            String roleId,
            Boolean passwordChangeRequired,
            String exchangeCode,
            Map<String, Object> userData) {
        super(username, password, enabled, true, true, true, authorities);
        this.loginId = loginId;
        this.userEmail = userEmail;
        this.allowedIps = allowedIps;
        this.displayName = displayName;
        this.mobileNo = mobileNo;
        this.endTime = endTime;
        this.failedAttempt = failedAttempt;
        this.startTime = startTime;
        this.activeStatus = activeStatus;
        this.userId = userId;
        this.roleId = roleId;
        this.passwordChangeRequired = passwordChangeRequired;
        this.exchangeCode = exchangeCode;
        this.userData = userData != null
                ? Collections.unmodifiableMap(new LinkedHashMap<>(userData))
                : Collections.emptyMap();
    }



    public String getLoginId() {
        return this.loginId;
    }


    public String getUserEmail() {
        return this.userEmail;
    }


    public String getAllowedIps() {
        return this.allowedIps;
    }


    public String getDisplayName() {
        return this.displayName;
    }


    public String getMobileNo() {
        return this.mobileNo;
    }


    public String getEndTime() {
        return this.endTime;
    }


    public Integer getFailedAttempt() {
        return this.failedAttempt;
    }


    public String getStartTime() {
        return this.startTime;
    }


    public Boolean isActiveStatus() {
        return this.activeStatus;
    }

    public Boolean getActiveStatus() {
        return this.activeStatus;
    }


    public String getUserId() {
        return this.userId;
    }


    public String getRoleId() {
        return this.roleId;
    }


    public Boolean isPasswordChangeRequired() {
        return this.passwordChangeRequired;
    }

    public Boolean getPasswordChangeRequired() {
        return this.passwordChangeRequired;
    }


    public String getExchangeCode() {
        return this.exchangeCode;
    }


    public Map<String,Object> getUserData() {
        return this.userData;
    }

}
