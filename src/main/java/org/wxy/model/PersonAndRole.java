package org.wxy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class PersonAndRole {

    @ExcelProperty("角色名称")
    private String roleName;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("域账户")
    private String account;

    @ExcelProperty("部门")
    private String depart;

    @ExcelProperty("部门层级")
    private String departPaths;
}
