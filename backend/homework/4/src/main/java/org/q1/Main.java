package org.q1;

import org.Logger;

public class Main {
    public static void main(String[] args) {
        int[] studentId = {1001, 1002};
        char[][] studentGrade = {{'A', 'B'}, {'A', ' '}};
        try{
            double[] var=StudentUtil.calculateGPA(studentId,studentGrade);
        }
        catch (Exception e){
            Logger.infoMessage(e.getMessage());
        }
        try {
            int[] ans = StudentUtil.getStudentsByGPA(2.5,3.5,studentId, studentGrade);
        }
        catch (Exception e){
            Logger.infoMessage(e.getMessage());
        }
    }
}