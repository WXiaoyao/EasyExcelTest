package org.wxy.stylefactory;

import org.apache.poi.ss.usermodel.*;

public class CellStyleFactory {
    private static final RedFontStyle RED_FONT_STYLE = new RedFontStyle();
    private static final BlueFontStyle BLUE_FONT_STYLE = new BlueFontStyle();

    public static CellStyle redFontStyle(Cell cell) {
        return RED_FONT_STYLE.createStyle(cell);
    }

    public static CellStyle blueFontStyle(Cell cell) {
        return BLUE_FONT_STYLE.createStyle(cell);
    }

    public static void release() {
        RED_FONT_STYLE.releaseStyle();
        BLUE_FONT_STYLE.releaseStyle();
    }
}

class RedFontStyle extends AbstractThreadLocalStyle {
    @Override
    public CellStyle doCreateSytle(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        Font font = workbook.createFont();
        font.setFontName("华文楷体");
        font.setColor(Font.COLOR_RED);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
}

class BlueFontStyle extends AbstractThreadLocalStyle {
    @Override
    public CellStyle doCreateSytle(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        Font font = workbook.createFont();
        font.setFontName("华文楷体");
        font.setColor((short) 33);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
}