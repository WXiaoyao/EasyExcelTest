package org.wxy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class CommonModel {
    @ExcelProperty("序号")
    private String id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("域账号")
    private String account;
}
