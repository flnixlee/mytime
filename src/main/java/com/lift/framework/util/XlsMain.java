package com.lift.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsMain {

    public static void main(String[] args) throws IOException {
	XlsMain xlsMain = new XlsMain();

	xlsMain.readXls();
    }

    @SuppressWarnings({ "resource", "deprecation" })
    public void readXls() throws IOException {
	InputStream is = new FileInputStream("D:\\excel\\国内代理信息(done).xls");
	HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

	// 循环工作表Sheet
	for (int numSheet = 1; numSheet <= 1; numSheet++) {
	    HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	    if (hssfSheet == null) {
		continue;
	    }

	    // 循环行Row
	    for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		if (hssfRow == null) {
		    continue;
		}

		// 循环列Cell
		for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
		    HSSFCell hssfCell = hssfRow.getCell((short) cellNum);
		    if (hssfCell == null) {
			continue;
		    }

		    System.out.print("    " + getValue(hssfCell));
		}
		System.out.println();
	    }
	}
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
	if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	    return String.valueOf(hssfCell.getBooleanCellValue());
	} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	    return String.valueOf(hssfCell.getNumericCellValue());
	} else {
	    return String.valueOf(hssfCell.getStringCellValue());
	}
    }

}