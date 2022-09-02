package org.wxy.stylefactory;

import org.apache.poi.ss.usermodel.*;

public class CellStyleFactory {
    private static final RedFontStyle RED_FONT_STYLE = new RedFontStyle();
    private static final PurpleFontStyle PURPLE_FONT_STYLE = new PurpleFontStyle();
    private static final NormalFontStyle NORMAL_FONT_STYLE = new NormalFontStyle();
    private static Font PURPLE_FONT;
    private static Font NORMAL_FONT;
    public static CellStyle redFontStyle(Cell cell) {
        return RED_FONT_STYLE.createStyle(cell);
    }

    public static CellStyle purpleFontStyle(Cell cell) {
        return PURPLE_FONT_STYLE.createStyle(cell);
    }

    public static CellStyle normalFontStyle(Cell cell) {
        return NORMAL_FONT_STYLE.createStyle(cell);
    }

    public static Font normalFont(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        Font font = workbook.createFont();
        font.setFontName("华文楷体");
//        font.setColor((short) 33);
        return font;
    }

    public static Font purpleFont(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        Font font = workbook.createFont();
        font.setFontName("华文楷体");
        font.setColor((short) 33);
        return font;
    }

    public static void release() {
        RED_FONT_STYLE.releaseStyle();
        PURPLE_FONT_STYLE.releaseStyle();
        NORMAL_FONT_STYLE.releaseStyle();
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

class PurpleFontStyle extends AbstractThreadLocalStyle {
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

class NormalFontStyle extends AbstractThreadLocalStyle {
    @Override
    public CellStyle doCreateSytle(Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        Font font = workbook.createFont();
        font.setFontName("华文楷体");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
}