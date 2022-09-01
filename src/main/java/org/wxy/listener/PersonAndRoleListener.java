package org.wxy.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.wxy.common.UserMap;
import org.wxy.model.PersonAndRole;
import org.wxy.model.User;

import java.util.*;

@Slf4j
public class PersonAndRoleListener implements ReadListener<PersonAndRole> {
    private Map<String, Map<String, Set<String>>> userMap;
    private Map<String, Set<String>> roleNameAccountMap;
    private Set<String> servingStaffAccountSet;
    private Set<String> positionTransferSet = new HashSet<>();
    private Set<String> technicianSet = new HashSet<>();

    public void invoke(PersonAndRole personAndRole, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(personAndRole));
        userMap = UserMap.getUserMap();
        roleNameAccountMap = UserMap.getRoleNameAccountMap();
        servingStaffAccountSet = UserMap.getServingStaffAccountSet();
        positionTransferSet = UserMap.getPositionTransferSet();
        technicianSet = UserMap.getTechnicianSet();
        Map<String, Set<String>> resourceMap;
        String account, name, roleName, depart, departPaths;
        account = personAndRole.getAccount();
        name = personAndRole.getName();
        roleName = personAndRole.getRoleName();
        depart = personAndRole.getDepart();
        departPaths = personAndRole.getDepartPaths();
        if (!userMap.containsKey(personAndRole.getAccount())) {
            resourceMap = new HashMap<>();
            resourceMap.put("account", new HashSet<>(Arrays.asList(account)));
            resourceMap.put("name", new HashSet<>(Arrays.asList(name)));
            resourceMap.put("roleName", new HashSet<>(Arrays.asList(roleName)));
            resourceMap.put("depart", new HashSet<>(Arrays.asList(depart)));
            resourceMap.put("departPaths", new HashSet<>(Arrays.asList(departPaths)));
            resourceMap.put("resourcePath1", new HashSet<>());
            resourceMap.put("resourcePath2", new HashSet<>());
            resourceMap.put("resourcePath3", new HashSet<>());
            resourceMap.put("resourcePath4", new HashSet<>());
            resourceMap.put("resourceName", new HashSet<>());
            if (positionTransferSet.contains(account)) {
                resourceMap.put("positionTransfer", new HashSet<>(Arrays.asList("Y")));
            } else {
                resourceMap.put("positionTransfer", new HashSet<>());
            }
            if (technicianSet.contains(account)) {
                resourceMap.put("isTechnician", new HashSet<>(Arrays.asList("Y")));
            } else {
                resourceMap.put("isTechnician", new HashSet<>());
            }
//            resourceMap.put("privilegeAggregation", new HashSet<>());
            userMap.put(personAndRole.getAccount(), resourceMap);
        } else {
            resourceMap = userMap.get(account);
            resourceMap.get("roleName").add(roleName);
        }
        if (!roleNameAccountMap.containsKey(roleName)) {
            roleNameAccountMap.put(roleName, new HashSet<>(Arrays.asList(account)));
        } else {
            roleNameAccountMap.get(roleName).add(account);
        }
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }
}
