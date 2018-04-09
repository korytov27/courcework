package utils;

import enums.Errors;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CustomLogger {

    private static SimpleDateFormat fileFormat = new SimpleDateFormat("yyyy-mm-dd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm/dd hh:mm:ss");

    private static String FILE_PATTERN = "log_%s.txt";
    private static String LOG_PATTERN = "[%s][%s]: %s .";


    private static String INFO = "INFO";
    private static String WARN = "WARNING";
    private static String ERROR = "ERROR";
    private static String DEBUG = "DEBUG";


    private static void write(String data){
        FILE_PATTERN = String.format(FILE_PATTERN, fileFormat.format(new Date()));

        try(FileWriter fw = new FileWriter(FILE_PATTERN, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            out.println(data);

        } catch (IOException e) {
            throw new RuntimeException(Errors.WriteMessage.getErrorMessage());
        }

    }

    private static void wrapMessage(String level, String message){
        write(String.format(LOG_PATTERN, level, dateFormat.format(new Date().getDate()), message));
    }


    public static void info(String message){
        wrapMessage(INFO, message);
    }

    public static void warn(String message){
        wrapMessage(WARN, message);
    }

    public static void error(String message){
        wrapMessage(ERROR, message);
    }

    public static void debug(String message){
        wrapMessage(DEBUG, message);
    }

}
