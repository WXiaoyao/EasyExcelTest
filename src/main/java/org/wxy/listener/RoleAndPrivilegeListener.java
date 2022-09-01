package org.wxy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.wxy.common.UserMap;
import org.wxy.model.PersonAndRole;
import org.wxy.model.RoleAndPrivilege;
import org.wxy.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class RoleAndPrivilegeListener implements ReadListener<RoleAndPrivilege> {
    private Map<String, Map<String, Set<String>>> userMap;
    private Map<String, Set<String>> roleNameAccountMap;

    public void invoke(RoleAndPrivilege roleAndPrivilege, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(roleAndPrivilege));
        userMap = UserMap.getUserMap();
        roleNameAccountMap = UserMap.getRoleNameAccountMap();
        String roleName, resourceName, resourcePath1, resourcePath2, resourcePath3, resourcePath4;
        String[] resourcePaths = roleAndPrivilege.getResourcepaths().split("/");
        resourcePath1 = resourcePaths.length > 1 ? resourcePaths[1] : "";
        resourcePath2 = resourcePaths.length > 2 ? resourcePaths[2] : "";
        resourcePath3 = resourcePaths.length > 3 ? resourcePaths[3] : "";
        resourcePath4 = resourcePaths.length > 4 ? resourcePaths[4] : "";
        roleName = roleAndPrivilege.getRoleName();
        resourceName = roleAndPrivilege.getResource();
        for (String account : roleNameAccountMap.get(roleName)) {
            Map<String, Set<String>> resourceMap = userMap.get(account);
            if (!resourcePath1.equals("")) {
                resourceMap.get("resourcePath1").add(resourcePath1);
            }
            if (!resourcePath2.equals("")) {
                resourceMap.get("resourcePath2").add(resourcePath2);
            }
            if (!resourcePath3.equals("")) {
                resourceMap.get("resourcePath3").add(resourcePath3);
            }
            if (!resourcePath4.equals("")) {
                resourceMap.get("resourcePath4").add(resourcePath4);
            }
            if (!resourceName.equals("")) {
                resourceMap.get("resourceName").add(resourceName);
            }
        }
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }
}
