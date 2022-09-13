package org.wxy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractVerticalCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.wxy.common.UserMap;
import org.wxy.listener.*;
import org.wxy.model.CommonModel;
import org.wxy.model.PersonAndRole;
import org.wxy.model.RoleAndPrivilege;
import org.wxy.model.User;
import org.wxy.writehandler.MyRowWriteHandler;

import java.util.*;

public class App 
{
    private Map<String, Map<String, Set<String>>> userMap;
    private Map<String, Set<String>> roleNameAccountMap;
    String path = this.getClass().getClassLoader().getResource("").getPath() + "real/";

    public static void main( String[] args )
    {
        App app = new App();
        app.servingStaffCheck();
        app.positionTransferCheck();
        app.isTechnicianCheck();
        app.processPersonAndRoleExcel();
        app.processRoleAndPrivilegeExcel();
        app.writeIntoTargetExcel();
    }

    private void processPersonAndRoleExcel() {
        String fileName = path + "PersonAndRole.xls";
        System.out.println(fileName);
        try (ExcelReader excelReader = EasyExcel.read(fileName, PersonAndRole.class, new PersonAndRoleListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }

    private void processRoleAndPrivilegeExcel() {
        String fileName = path + "RoleAndPrivilege.xls";
        System.out.println(fileName);
        try (ExcelReader excelReader = EasyExcel.read(fileName, RoleAndPrivilege.class, new RoleAndPrivilegeListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }

    private void writeIntoTargetExcel() {
        String fileName = path + "mergedExcel.xlsx";
        userMap = UserMap.getUserMap();
        roleNameAccountMap = UserMap.getRoleNameAccountMap();
        write(fileName);
    }

    private void write(String fileName) {
        WriteFont writeFont = new WriteFont();
        writeFont.setFontName("华文楷体");
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setWriteFont(writeFont);
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).
                registerWriteHandler(new AbstractVerticalCellStyleStrategy() {
                    @Override
                    protected WriteCellStyle contentCellStyle(Head head) {
                        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
                        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
                        contentWriteCellStyle.setWrapped(true);
                        return contentWriteCellStyle;
                    }
                }).registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle,
                contentWriteCellStyle)).inMemory(true).registerWriteHandler(new MyRowWriteHandler()).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("合并表").build();
            scanAndWrite(excelWriter, writeSheet);
        }
    }

    private void scanAndWrite(ExcelWriter excelWriter, WriteSheet writeSheet) {
        List<User> userList = ListUtils.newArrayListWithExpectedSize(100);
        for (String account : userMap.keySet()) {
            User user = userGeneration(account);
            userList.add(user);
            if (userList.size() == 100) {
                excelWriter.write(userList, writeSheet);
                userList = ListUtils.newArrayListWithExpectedSize(100);
            }
        }
        excelWriter.write(userList, writeSheet);
    }

    private User userGeneration(String account) {
        User user = new User();
        String name, depart, departPaths, roleName, resourceName,
                resourcePath1, resourcePath2, resourcePath3, resourcePath4,
                positionTransfer, isTechnician, privilegeAggregation;
        Map<String, Set<String>> resourceMap = userMap.get(account);
        name = String.join("\n", resourceMap.get("name"));
        depart = String.join("\n", resourceMap.get("depart"));
        departPaths = String.join("\n", resourceMap.get("departPaths"));
        roleName = String.join("\n", resourceMap.get("roleName"));
        resourceName = String.join("\n", resourceMap.get("resourceName"));
        resourcePath1 = String.join("\n", resourceMap.get("resourcePath1"));
        resourcePath2 = String.join("\n", resourceMap.get("resourcePath2"));
        resourcePath3 = String.join("\n", resourceMap.get("resourcePath3"));
        resourcePath4 = String.join("\n", resourceMap.get("resourcePath4"));
        positionTransfer = String.join("\n", resourceMap.get("positionTransfer"));
        isTechnician = String.join("\n", resourceMap.get("isTechnician"));
        Set<String> resourceNameSet = resourceMap.get("resourceName");
        if (resourceNameSet.contains("经办") && resourceNameSet.contains("复核")) {
            privilegeAggregation = "Y";
        } else {
            privilegeAggregation = "";
        }
        user.setAccount(account);
        user.setName(name);
        user.setDepart(depart);
        user.setDepartPaths(departPaths);
        user.setRoleName(roleName);
        user.setResourceName(resourceName);
        user.setResourcePath1(resourcePath1);
        user.setResourcePath2(resourcePath2);
        user.setResourcePath3(resourcePath3);
        user.setResourcePath4(resourcePath4);
        user.setPositionTransfer(positionTransfer);
        user.setIsTechnician(isTechnician);
        user.setPrivilegeAggregation(privilegeAggregation);
        return user;
    }

    private void servingStaffCheck() {
        String fileName = path + "ServingStaffList.xlsx";
        System.out.println(fileName);
        try (ExcelReader excelReader = EasyExcel.read(fileName, CommonModel.class, new ServingStaffListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }

    private void positionTransferCheck() {
        String fileName = path + "PositionTransferList.xlsx";
        System.out.println(fileName);
        try (ExcelReader excelReader = EasyExcel.read(fileName, CommonModel.class, new PositionTransferListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }

    private void isTechnicianCheck() {
        String fileName = path + "TechnicianList.xlsx";
        System.out.println(fileName);
        try (ExcelReader excelReader = EasyExcel.read(fileName, CommonModel.class, new TechnicianCheckListener()).build()) {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        }
    }
}
