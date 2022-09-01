package org.wxy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ColumnWidth(25)
@HeadRowHeight(40)
@ContentRowHeight(100)
public class User {
    @ExcelProperty("姓名")
    @ColumnWidth(10)
    private String name;

    @ExcelProperty("角色名称")
    @ColumnWidth(25)
    private String roleName;

    @ExcelProperty("域账户")
    @ColumnWidth(10)
    private String account;

    @ExcelProperty("部门")
    @ColumnWidth(15)
    private String depart;

    @ExcelProperty("部门层级")
    @ColumnWidth(25)
    private String departPaths;

    @ExcelProperty("资源层级1")
    private String resourcePath1;

    @ExcelProperty("资源层级2")
    private String resourcePath2;

    @ExcelProperty("资源层级3")
    private String resourcePath3;

    @ExcelProperty("资源层级4")
    private String resourcePath4;

    @ExcelProperty("资源名称")
    private String resourceName;

    @ExcelProperty("是否经过异动")
    private String positionTransfer;

    @ExcelProperty("是否权限聚集")
    private String privilegeAggregation;

    @ExcelProperty("是否为技术人员")
    private String isTechnician;
}