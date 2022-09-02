package org.wxy.writehandler;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.wxy.stylefactory.CellStyleFactory;
import org.wxy.common.UserMap;
import org.wxy.utils.KMP;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyRowWriteHandler implements RowWriteHandler, Closeable {

    private Map<String, Map<String, Set<String>>> userMap = new HashMap<>();
    private Set<String> servingStaffAccountSet;

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                         Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            return;
        }
        servingStaffAccountSet = UserMap.getServingStaffAccountSet();
        userMap = UserMap.getUserMap();
        String account = row.getCell(2).getStringCellValue();
        Set<String> resourceNameSet = userMap.get(account).get("resourceName");
        if (!isHead && resourceNameSet.contains("经办") && resourceNameSet.contains("复核")) {
            Cell cell1, cell2;
            cell1 = row.getCell(9);
            cell2 = row.getCell(11);

            Font normalFont = CellStyleFactory.normalFont(cell1);
            Font purpleFont = CellStyleFactory.purpleFont(cell1);

            String cellString = cell1.getStringCellValue();
            XSSFRichTextString richTextString = new XSSFRichTextString(cellString);
//            HSSFRichTextString richTextString = new HSSFRichTextString(cell1.getStringCellValue());
            richTextString.applyFont(normalFont);
            List<Integer> purpleIndexes = KMP.find(cellString, "经办");
            purpleIndexes.addAll(KMP.find(cellString, "复核"));
            for (Integer index : purpleIndexes) {
                richTextString.applyFont(index, index + 2, purpleFont);
            }
            cell1.setCellValue(richTextString);

            CellStyle purpleCellStyle = CellStyleFactory.purpleFontStyle(cell2);
            cell2.setCellStyle(purpleCellStyle);
        }
        if (!isHead && !servingStaffAccountSet.contains(account)) {
            for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                CellStyle cellStyle = CellStyleFactory.redFontStyle(cell);
                cell.setCellStyle(cellStyle);
            }
        }
    }

    @Override
    public void close() throws IOException {
        CellStyleFactory.release();
    }
}
