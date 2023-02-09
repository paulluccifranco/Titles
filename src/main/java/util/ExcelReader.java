package util;

import model.Title;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelReader {

    public List<Title> generateTitles(InputStream file) throws IOException {
        List<Title> titles = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook();
        try{
            wb = new XSSFWorkbook(file);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);

        int rows = sheet.getLastRowNum();

        for (int i = 1; i < rows + 1; ++i) {
            XSSFRow row = sheet.getRow(i);
            Title title = new Title();
            try {
                XSSFCell nameCell = row.getCell(0);
                XSSFCell dniCell = row.getCell(1);
                XSSFCell numberCell = row.getCell(2);
                XSSFCell bookCell = row.getCell(3);
                XSSFCell invoiceCell = row.getCell(4);
                XSSFCell cymatCell = row.getCell(5);
                XSSFCell dateCymatCell = row.getCell(6);
                XSSFCell sp1Cell = row.getCell(7);
                XSSFCell dateSp1Cell = row.getCell(8);
                XSSFCell biologyCell = row.getCell(9);
                XSSFCell dateBiologyCell = row.getCell(10);
                XSSFCell fundamentsCell = row.getCell(11);
                XSSFCell dateFundamentsCell = row.getCell(12);
                XSSFCell caresCell = row.getCell(13);
                XSSFCell dateCaresCell = row.getCell(14);
                XSSFCell practiceCell = row.getCell(15);
                XSSFCell datePracticeFCell = row.getCell(16);
                XSSFCell averageCell = row.getCell(17);
                XSSFCell dateAverageCell = row.getCell(18);
                if(nameCell == null) break;
                if(nameCell.getStringCellValue().equals("")) break;
                title.setName(nameCell.getStringCellValue());
                dniCell.setCellType(CellType.STRING);
                title.setDni(dniCell.getStringCellValue());
                numberCell.setCellType(CellType.STRING);
                title.setNumber(new BigDecimal(numberCell.getStringCellValue()));
                bookCell.setCellType(CellType.STRING);
                title.setBook(new BigDecimal(bookCell.getStringCellValue()));
                invoiceCell.setCellType(CellType.STRING);
                title.setInvoice(new BigDecimal(invoiceCell.getStringCellValue()));
                title.setCymat(BigDecimal.valueOf(cymatCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateCymat(dateCymatCell.getDateCellValue());
                title.setSp1(BigDecimal.valueOf(sp1Cell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateSp1(dateSp1Cell.getDateCellValue());
                title.setBiology(BigDecimal.valueOf(biologyCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateBiology(dateBiologyCell.getDateCellValue());
                title.setFundaments(BigDecimal.valueOf(fundamentsCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateFundaments(dateFundamentsCell.getDateCellValue());
                title.setCares(BigDecimal.valueOf(caresCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateCares(dateCaresCell.getDateCellValue());
                title.setPractice(BigDecimal.valueOf(practiceCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDatePracticeF(datePracticeFCell.getDateCellValue());
                title.setAverage(BigDecimal.valueOf(averageCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                title.setDateAverage(dateAverageCell.getDateCellValue());

                titles.add(title);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
        return titles;

    }
}
