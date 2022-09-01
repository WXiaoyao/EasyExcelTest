package org.wxy.stylefactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public interface CellStyleCreator {
    CellStyle createStyle(Cell cell);
}
