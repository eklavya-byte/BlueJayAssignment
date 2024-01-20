package org.assignment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

public class ResultWritter {
    /*
    * Write a program that:
        1. Take the file as an input
        2. Programmatically analyze the file and print in console the name and position of employees
            a) who has worked for 7 consecutive days.
            b) who have less than 10 hours of time between shifts but greater than 1 hour
            c) Who has worked for more than 14 hours in a single shift
        3. Write a clean code with code comments and assumptions (if any) you are making
        4. Create a public GIT repository
    * */


    public static void main(String[] args) {
        EmpDao empDao = new EmpDao();

        Path p1 = Paths.get("src/main/java/org/assignment/output.txt");
        try {
            //standardOption for now deleting previous writes
            BufferedWriter writer = Files.newBufferedWriter(p1, StandardOpenOption.APPEND);
//--------------------------------------------------------------------------------------------------
            writer.write(" Names of employee and Position id's who worked for 7 consecutive Days ! \n");
            List<List<String>> lists = empDao.namesBaseOnWork(7);
            int count = 0;
            for(List<String> str : lists){
                for(String s : str){
                    writer.write("   "+s);
                }
                writer.write("\n");
                count ++;
            }
            writer.write("Total no of Employee who worked for 7 consecutive days are : "+ count +"\n \n \n \n");
//--------------------------------------------------------------------------------------------------
            //who worked for between 1-10
            writer.write("Names of employees and position id who have less than 10 hours of time between shifts but greater than 1 hour \n ");
            int siftHCount = 0;
            List<List<String>> listTimeBetShift = empDao.namesBaseOnTimeBetweenShifts(1, 10);
            for(List<String> list : listTimeBetShift){
                for(String s : list){
                    writer.write("   "+s);
                }
                writer.write("\n");
                siftHCount ++;
            }
            writer.write("Total no. of employee : " + siftHCount+"\n \n \n \n");

//-----------------------------------------------------------------------------------------------------------------------
            // Who has worked for more than 14 hours in a single shift
            int singleSiftEmpCount = 0;
            writer.write("Employee who worked for more than 14 hours in a single shift \n");
            Set<List<String>> longerSift = empDao.namesBaseOnWorkHour(14);
            for(List<String> list : longerSift){
                for(String s : list){
                    writer.write("   "+s);
                }
                writer.write("\n");
                singleSiftEmpCount ++;
            }
            writer.write("Total no. of employee : " + siftHCount+"\n \n \n \n");
            writer.flush();
            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
