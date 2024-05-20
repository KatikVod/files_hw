import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipParsingTests {
    private final ClassLoader cl = ZipParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка количества страниц в pdf-файле")
    void pdfParsingTest() throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("files.zip"))
        )) {
            ZipEntry entry;
            PDF pdf = null;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals("article.pdf")) {
                    pdf = new PDF(zipInputStream);
                }
            }

            assertThat(pdf).isNotNull();
            assertThat(pdf.numberOfPages).isEqualTo(4);
        }
    }

    @Test
    @DisplayName("Проверка заголовков xlsx-таблицы")
    void xlsxParsingTest() throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("files.zip"))
        )) {
            ZipEntry entry;
            XLS xls = null;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals("users.xlsx")) {
                    xls = new XLS(zipInputStream);
                }
            }

            assertThat(xls).isNotNull();
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getNumericCellValue())
                    .isEqualTo(0.0);
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue())
                    .isEqualTo("First Name");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue())
                    .isEqualTo("Last Name");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(3).getStringCellValue())
                    .isEqualTo("Gender");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(4).getStringCellValue())
                    .isEqualTo("Country");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(5).getStringCellValue())
                    .isEqualTo("Age");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(6).getStringCellValue())
                    .isEqualTo("Date");
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(7).getStringCellValue())
                    .isEqualTo("Id");
        }
    }

    @Test
    @DisplayName("Проверка первой строки csv-файла")
    void csvParsingTest() throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("files.zip"))
        )) {
            ZipEntry entry;
            List<String[]> data = null;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals("weekday.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zipInputStream));
                    data = reader.readAll();
                }
            }

            assertThat(data).isNotNull();
            assertThat(data.getFirst()).isEqualTo(new String[]{"Monday", "Mon.", "1" });
        }
    }
}
