package abl.frd.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import abl.frd.helper.MyUserDetails;
import abl.frd.service.CommonService;
import abl.frd.service.MenuService;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value="/getMenu", method= RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMenu(@AuthenticationPrincipal MyUserDetails userDetails){
        Map<String, Object> resp = new HashMap<>();
        if(userDetails == null){
            resp = CommonService.getResp(2,"Session Expired. Please Login Again", null);
            return ResponseEntity.ok(resp);
        }
        String roleId = userDetails.getRoleId();
        resp = menuService.getMenu(roleId);
        return ResponseEntity.ok(resp);
    }
}
