package org;

public class Logger {
    public static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(String.valueOf(Logger.class));

    public static void infoMessage(String message){
        logger.info(message);
    }
    public static void infoMessage(int value){
        logger.info(Integer.toString(value));
    }
    public static void infoMessage(double value){
        logger.info(Double.toString(value));
    }
    public static void warnMessage(String message){
        logger.warning(message);
    }
    public static void severeMessage(String message){
        logger.severe(message);
    }

}
