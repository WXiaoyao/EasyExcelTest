package org.wxy.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserMap {
    private static Map<String, Map<String, Set<String>>> userMap = new HashMap<>();
    private static Map<String, Set<String>> roleNameAccountMap = new HashMap<>();
    private static Set<String> servingStaffAccountSet = new HashSet<>();
    private static Set<String> positionTransferSet = new HashSet<>();
    private static Set<String> technicianSet = new HashSet<>();

    public static Map<String, Map<String, Set<String>>> getUserMap() {
        return userMap;
    }

    public static Map<String, Set<String>> getRoleNameAccountMap() {
        return roleNameAccountMap;
    }

    public static Set<String> getServingStaffAccountSet() {
        return servingStaffAccountSet;
    }

    public static Set<String> getPositionTransferSet() {
        return positionTransferSet;
    }

    public static Set<String> getTechnicianSet() {
        return technicianSet;
    }
}
