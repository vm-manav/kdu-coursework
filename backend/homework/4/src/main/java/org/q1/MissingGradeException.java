package org.q1;

public class MissingGradeException extends Exception{
    private final int studentId;

    public MissingGradeException(int studentId){
        super("Grade Not Found Student with ID : "+studentId);
        this.studentId=studentId;
    }
    private int getStudentId(){
        return this.studentId;
    }
}
