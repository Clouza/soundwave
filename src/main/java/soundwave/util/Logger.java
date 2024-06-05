package soundwave.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * This class contains logging activities that help
 * with the development experience.
 */
public class Logger {
    private static final String LOGGER_DIRECTORY = "logs";

    public Logger() {}

    /**
     * Record exception that has log method inside of it. If this method
     * can't afford any logging activity, it will immediately
     * send a report to discord via webhook.
     *
     * @param exception
     * @return Exception Message
     */
    public static String log(Exception exception) {
        Date now = new Date();
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(now);

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date.format(now);

        Object logFile = readAndWriteLogFile(exception, formattedDate, formattedDateTime);
        if(!(logFile instanceof Boolean)) {
            // send report to discord
            try {
                HttpRequest request = new HttpRequest();
                Dotenv env = new Dotenv();
                request.setMethod("POST")
                        .setUrl(env.ENV("DISCORD_WEBHOOK"))
                        .setContentType("application/json")
                        .setMessage("Hi Guys!")
                        .send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return exception.getMessage();
    }

    /**
     * Read and Write exception into log files.
     *
     * @param exception
     * @param formattedDate 2024-06-04
     * @param formattedDateTime 2024-06-04 12:00:01
     * @return Boolean | Exception
     */
    private static Object readAndWriteLogFile(Exception exception, String formattedDate, String formattedDateTime) {
        try {
            // check file exists
            String pathName = String.format("%s/%s.log", "logs", formattedDate);
            File file = new File(pathName);
            String metadata = String.format("[%s] %s %s", formattedDateTime, exception.getMessage(), Arrays.toString(exception.getStackTrace()));

            if (!file.exists()) {
                FileWriter fileLogName = new FileWriter(pathName);

                fileLogName.append(metadata);

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
                content.append(metadata).append("\n");

                FileWriter fileLogName = new FileWriter(pathName);
                fileLogName.append(content);

                fileLogName.flush();
                fileLogName.close();
            }
        } catch (Exception e) {
            System.out.println("Log Error :(");
            e.printStackTrace();
        }

        return true;
    }
}
