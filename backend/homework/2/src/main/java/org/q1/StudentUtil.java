package org.q1;

import org.q3.Book;

import java.util.logging.Logger;

public class StudentUtil {
    static Logger logger = Logger.getLogger(String.valueOf(Book.class));
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        int numberOfStudents=studentIdList.length;
        double[] computedGpaArray=new double[numberOfStudents];
        int traversalIndex=0;
        for(char[] currentStudentGrades : studentsGrades) {
            double gpaValue = getGpaValue(currentStudentGrades);
            computedGpaArray[traversalIndex]=gpaValue;
            traversalIndex++;
        }
        return computedGpaArray;
    }

    private static double getGpaValue(char[] currentStudentGrades) {
        int numberOfGrades= currentStudentGrades.length;
        int currentScore=0;
        for(char studentGrade : currentStudentGrades) {
            if(studentGrade=='A') {
                currentScore+=4;
            } else if (studentGrade=='B') {
                currentScore+=3;
            }else if (studentGrade=='c'){
                currentScore+=2;
            }
        }
        double gpaValue=currentScore/(numberOfGrades*1.0);
        return gpaValue;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if(lower<=higher && lower>=0 && higher<=4){
            double[] gpaValueArray=calculateGPA(studentIdList,studentsGrades);
            int totalNumberOfStudents=studentIdList.length;
            int countValidValues=0;
            for(double currentGpa : gpaValueArray) {
                if(currentGpa>=lower && currentGpa<=higher) {
                    countValidValues++;
                }
            }
            int[] studentIdValidArray=new int[countValidValues];
            int iteratorIndex=0;

            for(int i=0;i<totalNumberOfStudents;i++) {
                if(gpaValueArray[i]>=lower && gpaValueArray[i]<=higher) {
                    studentIdValidArray[iteratorIndex]=studentIdList[i];
                    iteratorIndex++;
                }
            }
            return studentIdValidArray;
        }
        else {
            return null;
        }
    }

    public static void main(String args[]) {
        int[] demo1 = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};
        double[] ans = calculateGPA(demo1, studentsGrades);
        for (double x : ans) {
            logger.info(x + " ");
        }
        double a=3.2;
        double b=3.5;
        int[] ans2=getStudentsByGPA(a,b,demo1,studentsGrades);
        for(int i:ans2)
        {
            logger.info(i + " ");
        }
    }
}

