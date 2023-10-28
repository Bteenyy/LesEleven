package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import quru.qa.model.GlossaryModel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SelenideFilesTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void pdfTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("TestRes.zip")) {
            assert stream != null;
            try (ZipInputStream reader = new ZipInputStream(stream)) {
                ZipEntry entry;
                while ((entry = reader.getNextEntry()) != null) {
                    if (entry.getName().contains(".pdf")) {
                        PDF pdf = new PDF(reader);
                        Assertions.assertEquals("Nuance Communications, Inc.", pdf.author);
                        Assertions.assertEquals(59, pdf.numberOfPages);
                        Assertions.assertNull(pdf.signatureTime);
                    }
                }
            }
        }
    }

    @Test
    void xlsxTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("TestRes.zip")) {
            assert stream != null;
            try (ZipInputStream reader = new ZipInputStream(stream)) {
                ZipEntry entry;
                while ((entry = reader.getNextEntry()) != null) {
                    if (entry.getName().contains(".xlsx")) {
                        XLS xls = new XLS(reader);
                        Assertions.assertEquals(0, xls.excel.getAllNames().size());
                        Assertions.assertEquals("TemplateImportOU", xls.excel.getSheetAt(0).getSheetName());
                        Assertions.assertEquals("org.apache.poi.xssf.usermodel.XSSFCellStyle", xls.excel.createCellStyle().getClass().getName());
                    }
                }
            }
        }
    }

    @Test
    void csvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("TestRes.zip")) {
            assert stream != null;
            try (ZipInputStream reader = new ZipInputStream(stream)) {
                ZipEntry entry;
                while ((entry = reader.getNextEntry()) != null) {
                    if (entry.getName().contains(".csv")) {
                        Reader readerCsv = new InputStreamReader(reader);
                        CSVReader csvReader = new CSVReader(readerCsv);
                        List<String[]> value = csvReader.readAll();
                        Assertions.assertEquals(3, value.size());
                        Assertions.assertArrayEquals(new String[]{"Day", "27"}, value.get(0));
                        Assertions.assertArrayEquals(new String[]{"Month", "April"}, value.get(1));
                        Assertions.assertArrayEquals(new String[]{"Year", "1992"}, value.get(2));
                    }
                }
            }
        }
    }


    @Test
    void jacksonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("TestJac.json");
             Reader reader = new InputStreamReader(stream)) {
            GlossaryModel glossaryModel = mapper.readValue(reader, GlossaryModel.class);
            Assertions.assertEquals("Razor", glossaryModel.getHeroName());
            Assertions.assertEquals("Ranged", glossaryModel.getTypeOfHero());
            Assertions.assertEquals(25, glossaryModel.getMaxLvl());
            Assertions.assertEquals("OffLane", glossaryModel.getLane());
            Assertions.assertEquals("Power Treads", glossaryModel.getMostUsedItems());
            Assertions.assertEquals("Tidehunter", glossaryModel.getBestVersus());
        }
    }
}
