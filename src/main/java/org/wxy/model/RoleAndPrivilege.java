package org.wxy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class RoleAndPrivilege {
    @ExcelProperty("角色名称")
    private String roleName;

    @ExcelProperty("资源名称")
    private String resource;

    @ExcelProperty("资源层级路径")
    private String resourcepaths;
}