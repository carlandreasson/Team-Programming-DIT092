package Utilities;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Objects.Project.Project;
import Objects.Project.UserStory;

public class SaveToExcel {

    private static String[] columns = {"ID", "Points", "Description"};

    public static void saveToExcel(Project project) throws Exception {

        // Setup the basics
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Burndown Chart ");
        XSSFRow headerRow = spreadsheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        
        }
        int rowNum = 1;
        for(UserStory story: project.getUserStories()) {
            XSSFRow row = spreadsheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(story.getId());

            row.createCell(1)
                    .setCellValue(story.getPoints());

            row.createCell(2)
                    .setCellValue(story.getDescription());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            spreadsheet.autoSizeColumn(i);
        }

        
        FileOutputStream out = new FileOutputStream(new File("src/main/java/Files/Writesheet.xlsx"));

        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("File written successfully");
    }
}