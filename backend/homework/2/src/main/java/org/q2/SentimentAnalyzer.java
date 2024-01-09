package org.q2;
import org.q3.Book;

import java.util.Arrays;
import java.util.logging.Logger;

public class SentimentAnalyzer {
    static Logger logger = Logger.getLogger(String.valueOf(Book.class));
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,String[] negOpinionWords) {
        int featureSetLength=featureSet.length;
        int[] featureOpinions = new int[featureSetLength];

        for(int i=0;i<featureSetLength;i++)
        {
            int currentVal=0;
            int individualSetLength=featureSet[i].length;
            for(int j=0;j<individualSetLength;j++)
            {
                String currentFeature=featureSet[i][j];
                int getValue=getOpinionOnFeature(review,currentFeature,posOpinionWords,negOpinionWords);
                if(getValue!=0)
                {
                    currentVal=getValue;
                    break;
                }
            }
            featureOpinions[i]=currentVal;
        }
        return featureOpinions;
    }


    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        int currentValue=checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(currentValue!=0)
        {
            return currentValue;
        }
        return checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String currentPattern = feature + " was ";

        int positiveOpinionLength=posOpinionWords.length;
        for(int i=0;i<positiveOpinionLength;i++)
        {
            String newPattern=currentPattern.concat(posOpinionWords[i]);
            if(review.contains(newPattern)){
                return 1;
            }
        }
        int negativeOpinionLength=negOpinionWords.length;
        for(int i=0;i<negativeOpinionLength;i++)
        {
            String newPattern=currentPattern.concat(negOpinionWords[i]);
            if(review.contains(newPattern)){
                return -1;
            }
        }
        return opinion;
    }


    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        String newString=" "+feature;

        int opinion = 0;
        int positiveOpinionLength=posOpinionWords.length;
        for(int i=0;i<positiveOpinionLength;i++)
        {
            String newPattern=posOpinionWords[i].concat(newString);
            if(review.contains(newPattern)){
                return 1;
            }
        }
        int negativeOpinionLength=negOpinionWords.length;
        for(int i=0;i<negativeOpinionLength;i++)
        {
            String newPattern=negOpinionWords[i].concat(newString);
            if(review.contains(newPattern)){
                return -1;
            }
        }
        return opinion;
    }


    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        //String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };

        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious" };

        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };
        int[] featureOpinions = detectProsAndCons(review.toLowerCase(), featureSet, posOpinionWords, negOpinionWords);
        logger.info("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}




