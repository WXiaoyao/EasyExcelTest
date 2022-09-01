package org.wxy.stylefactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public abstract class AbstractThreadLocalStyle implements CellStyleCreator {

    private final ThreadLocal<CellStyle> THREAD_LOCAL_STYLE = new ThreadLocal<>();

    @Override
    public CellStyle createStyle(Cell cell) {
        CellStyle cellStyle = THREAD_LOCAL_STYLE.get();
        if (cellStyle == null) {
            cellStyle = doCreateSytle(cell);
            THREAD_LOCAL_STYLE.set(cellStyle);
        }
        return cellStyle;
    }

    public abstract CellStyle doCreateSytle(Cell cell);

    public void releaseStyle() {
        THREAD_LOCAL_STYLE.remove();
    }
}