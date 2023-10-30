package TipsOrTools;

import jxl.write.Label;
import jxl.write.WriteException;
import org.junit.Test;


import java.io.IOException;

public class CreateCatalogueExcelTest {

    @Test
    public void getDir() {
    }

    @Test
    public void createExcelByMatrix() throws WriteException, IOException {
        String[][] matrix = CreateCatalogueExcel.getDirFile("G:\\test",10,2);
        CreateCatalogueExcel.createExcelByMatrix("G:\\test",matrix);
    }

    @Test
    public void getDirFile() {
        String[][] matrix = CreateCatalogueExcel.getDirFile("F:\\音声\\步非烟\\BuFEiyaNNAna-1-120",140,1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length;j++){
                System.out.print(matrix[i][j]+"           ");
            }
            System.out.println();
        }
    }
}
