package abl.frd.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import abl.frd.model.Menu;
import abl.frd.repository.MenuRepository;

@SuppressWarnings("unchecked")
@Service
public class MenuService {
    @Autowired 
    MenuRepository menuRepository;

    public Map<String, Object> getMenu(String roleId){
        Map<String, Object> resp = new HashMap<>();
        List<Menu> menuList = menuRepository.findMenuByRoleId(roleId);
        Map<String, Object> menuMap = new LinkedHashMap<>();
        Map<String, Object> smenuMap = new LinkedHashMap<>();
        if(menuList.size() == 0)    return CommonService.getResp(1,"No data found", null);
        for(Menu menu: menuList){
            Map<String, Object> item = new LinkedHashMap<>();
            String parentId = menu.getParentId();
            String code = menu.getCode();
            item.put("code", code);
            item.put("name", menu.getName());
            item.put("url", menu.getUrl());
            item.put("parentId", parentId);
            item.put("icon", menu.getIcon());
            item.put("roleId", roleId);
            if(("0").equals(menu.getParentId())){
                menuMap.put(menu.getCode(), item);
            }else{
                List<Map<String, Object>> children = (List<Map<String, Object>>) smenuMap.get(parentId);
                if(children == null){
                    children = new ArrayList<>();
                    smenuMap.put(parentId, children);
                }
                children.add(item);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("menu", menuMap);
        data.put("smenu", smenuMap);
        resp = CommonService.getResp(0,"", null);
        resp.put("data", data);
        return resp;
    }
}
