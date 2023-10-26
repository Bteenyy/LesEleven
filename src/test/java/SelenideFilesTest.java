
import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SelenideFilesTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    void RarTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("Test.zip")) {
            assert stream != null;
            try (ZipInputStream reader = new ZipInputStream(stream)) {
                ZipEntry entry;
                while ((entry = reader.getNextEntry()) != null) {
                    if (entry.getName().contains(".pdf")) {
                        PDF pdf=new PDF(reader);

                    }
                }
            }
        }
    }
}
