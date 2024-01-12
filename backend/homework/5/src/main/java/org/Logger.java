package org;

public class Logger {
    private static final java.util.logging.Logger loggerObject = java.util.logging.Logger.getLogger(String.valueOf(Logger.class));

    public static void infoMessage(String message){
        loggerObject.info(message);
    }
    public static void warnMessage(String message){
        loggerObject.warning(message);
    }
    public static void severeMessage(String message){
        loggerObject.severe(message);
    }

}