package api.utilities;

	
	import java.io.*;
	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class XLUtility {
		
	    private Workbook workbook;
	    private Sheet sheet;
	    private String filePath;

	    public XLUtility(String filePath, String sheetName) throws Exception {
	        this.filePath = filePath;
	        FileInputStream fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	    }

	    public XLUtility(String path) {
			// TODO Auto-generated constructor stub
		}

		// Get cell data
	    public String getCellData(int rowNum, int colNum) {
	        Row row = sheet.getRow(rowNum);
	        Cell cell = row.getCell(colNum);
	        return cell.toString();
	    }

	    // Set cell data
	    public void setCellData(int rowNum, int colNum, String value) throws Exception {
	        Row row = sheet.getRow(rowNum);
	        if (row == null) row = sheet.createRow(rowNum);

	        Cell cell = row.getCell(colNum);
	        if (cell == null) cell = row.createCell(colNum);

	        cell.setCellValue(value);

	        FileOutputStream fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        fos.close();
	    }

	    // Get row count
	    public int getRowCount() {
	        return sheet.getLastRowNum();
	    }

	    // Close workbook
	    public void close() throws Exception {
	        workbook.close();
	    }

		public int getRowCount(String string) {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getCellCount(String string, int i) {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getCellData(String string, int i, int j) {
			// TODO Auto-generated method stub
			return null;
		}
	}

