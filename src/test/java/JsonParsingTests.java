import com.fasterxml.jackson.databind.ObjectMapper;
import model.Conference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParsingTests {

    private final ClassLoader cl = JsonParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого json-файла")
    void jsonParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(cl.getResourceAsStream("conference.json"))
        )) {
            ObjectMapper objectMapper = new ObjectMapper();
            Conference conference = objectMapper.readValue(reader, Conference.class);

            assertThat(conference.getId()).isEqualTo(345678);
            assertThat(conference.getName()).isEqualTo("Heisenbug 2024 Spring");
            assertThat(conference.getReports()[0]).isEqualTo("Events: Love Triangle in Integration Testing");
            assertThat(conference.getReports()[1]).isEqualTo("The art of JUnit extensions 2");
            assertThat(conference.getReports()[2]).isEqualTo("Zero to Hero. Production QA");

            assertThat(conference.getLocation().getVenue()).isEqualTo("Congress Center");
            assertThat(conference.getLocation().getCity()).isEqualTo("Moscow");
            assertThat(conference.getLocation().getCountry()).isEqualTo("Russia");

            assertThat(conference.getSponsors()[0].getName()).isEqualTo("Reksoft");
            assertThat(conference.getSponsors()[0].getUrl()).isEqualTo("https://www.reksoft.ru/");
            assertThat(conference.getSponsors()[1].getName()).isEqualTo("Ozon Tech");
            assertThat(conference.getSponsors()[1].getUrl()).isEqualTo("https://ozon.tech/");
            assertThat(conference.getSponsors()[2].getName()).isEqualTo("GitVerse");
            assertThat(conference.getSponsors()[2].getUrl()).isEqualTo("https://gitverse.ru/");
        }
    }
}
