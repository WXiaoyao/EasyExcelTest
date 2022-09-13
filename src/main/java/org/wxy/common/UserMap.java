package org.wxy.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserMap {
    private static Map<String, Map<String, Set<String>>> userMap;
    private static Map<String, Set<String>> roleNameAccountMap;
    private static Set<String> servingStaffAccountSet;
    private static Set<String> positionTransferSet;
    private static Set<String> technicianSet;

    public static Map<String, Map<String, Set<String>>> getUserMap() {
        if (userMap == null) {
            userMap = new HashMap<>();
        }
        return userMap;
    }

    public static Map<String, Set<String>> getRoleNameAccountMap() {
        if (roleNameAccountMap == null) {
            roleNameAccountMap = new HashMap<>();
        }
        return roleNameAccountMap;
    }

    public static Set<String> getServingStaffAccountSet() {
        if (servingStaffAccountSet == null) {
            servingStaffAccountSet = new HashSet<>();
        }
        return servingStaffAccountSet;
    }

    public static Set<String> getPositionTransferSet() {
        if (positionTransferSet == null) {
            positionTransferSet = new HashSet<>();
        }
        return positionTransferSet;
    }

    public static Set<String> getTechnicianSet() {
        if (technicianSet == null) {
            technicianSet = new HashSet<>();
        }
        return technicianSet;
    }
}
