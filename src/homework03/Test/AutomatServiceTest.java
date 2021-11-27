package homework03.Test;

import homework03.AutomatFinitDeterminist;
import homework03.AutomatService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AutomatServiceTest {

    @Test
    void checkCuvantValid() throws IOException {
        AutomatFinitDeterminist afd = new AutomatFinitDeterminist();
        afd.readData("automat01.txt");
        AutomatService automatService = new AutomatService("q0", afd);

        Boolean result = automatService.checkWord("10110010");
        assertEquals(true, result);
    }

    @Test
    void checkBlocajInAlfabet() throws IOException {
        AutomatFinitDeterminist afd = new AutomatFinitDeterminist();
        afd.readData("automat01.txt");
        AutomatService automatService = new AutomatService("q0", afd);
        Boolean result = automatService.checkWord("120110");
        assertEquals(false, result);
    }

    @Test
    void checkCuvantInvalid() throws IOException {
        AutomatFinitDeterminist afd = new AutomatFinitDeterminist();
        afd.readData("automat01.txt");
        AutomatService automatService = new AutomatService("q0", afd);

        Boolean result = automatService.checkWord("1011");
        assertEquals(false, result);

    }
}

