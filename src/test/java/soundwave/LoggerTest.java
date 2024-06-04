package soundwave;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import soundwave.util.Dotenv;
import soundwave.util.HttpRequest;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoggerTest {
    private Dotenv env;

    @Before
    public void setUp() {
        this.env = new Dotenv();
    }

    @Ignore
    @Test
    public void loggerShouldBeOkTest() {
        try {
            // check file exists
            String pathName = String.format("%s/%s.log", "logs", "2024-06-04");
            File file = new File(pathName);

            if (!file.exists()) {
                FileWriter fileLogName = new FileWriter(pathName);
                fileLogName.append("[2024-06-04 12:00:01] INFO Tidak ada error, file log dibuat.");

                fileLogName.flush();
                fileLogName.close();
            }

            if (file.exists()) {
                BufferedReader fileLog = new BufferedReader(new FileReader(pathName));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = fileLog.readLine()) != null) {
                    content.append(line).append("\n");
                }

                fileLog.close();

                String newText = "Onde mande!";
                content.append(newText).append("\n");

                FileWriter fileLogName = new FileWriter(pathName);
                fileLogName.append(content);

                fileLogName.flush();
                fileLogName.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Ignore
    @Test
    public void sendReportToDiscordTest() {
        try {
            HttpRequest request = new HttpRequest();
            request.setMethod("POST")
                    .setUrl(this.env.ENV("DISCORD_WEBHOOK"))
                    .setContentType("application/json")
                    .setMessage("Hi Guys!")
                    .send();
        } catch (Exception exception) {
            fail();
            exception.printStackTrace();
        }

        assertTrue(true);
    }
}
