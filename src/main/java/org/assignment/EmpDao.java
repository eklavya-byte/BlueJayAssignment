package org.assignment;

import com.sun.source.tree.LiteralTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assignment.FileLoader.readDataFromCSV;

public class EmpDao {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

    // getting all employee details in a list
    public static List<Employee> listOfEmpDetails() {
        return readDataFromCSV("src/main/java/org/assignment/Assignment_Timecard.csv");
    }

    //------------------------------------------------------------------------------------------------------

    // names of emp and position id who work for consecutive (n-days) 7 days
    // assuming he has present at that day ! only ! //not counting shifts
    public List<List<String>> namesBaseOnWork(int consecutiveDays) {
        List<Employee> employees = listOfEmpDetails();

        //        Set<List<String>> result = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        int count = 0;
        String currentEmpId = "";
        String prevTimeIn = "";

        for (Employee employee : employees) {
            if (currentEmpId.equals("")) {
                currentEmpId = employee.getId();
                prevTimeIn = employee.getTimeIn();
                count = 1;
            } else if (currentEmpId.equals(employee.getId()) && isConsecutive(prevTimeIn, employee.getTimeIn())) {
                count++;
                prevTimeIn = employee.getTimeIn();

                if (count == consecutiveDays) {
                    result.add(List.of(employee.getId(), employee.getEmployeeName()));
                    count = 0;
                    currentEmpId = "";
                    prevTimeIn = "";
                }
            } else {
                count = 0;
                currentEmpId = "";
                prevTimeIn = "";
            }
        }
    // sorting and removing duplicates
        Set<List<String>> uniqueNames = new LinkedHashSet<>();
        uniqueNames.addAll(result);
        result = new ArrayList<>(uniqueNames);

        return result;
    }

    private boolean isConsecutive(String prevTimeIn, String currentTimeIn) {
        try {
            Date prevDate = DATE_FORMAT.parse(prevTimeIn);
            Date currentDate = DATE_FORMAT.parse(currentTimeIn);
            return currentDate.after(prevDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------------------------------------------------------------------------
//    c) Who has worked for more than 14 hours in a single shift
    public Set<List<String>> namesBaseOnWorkHour(int hour) {
        List<Employee> employees = listOfEmpDetails();
        Set<List<String>> names = new LinkedHashSet<>();

        for (Employee e : employees) {
            try {
                String[] time = e.getTotalHours().split(":");
                if (time.length == 2) {
                    int hours = Integer.parseInt(time[0]);
                    if (hours >= hour) {
                        names.add(List.of(e.getId(),e.getEmployeeName()));
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException z) {
                z.printStackTrace();
            }
        }
        return names;
    }

    //--------------------------------------------------------------------------
    //b) who have less than 10 hours of time between shifts but greater than 1 hour
    // since our data is already sorted ! comparing dates with next and prev is okay here !

    public List<List<String>> namesBaseOnTimeBetweenShifts(int minHours, int maxHours) {
        SimpleDateFormat DATE_FORMAT_WITH_TIME = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");


        List<Employee> employees = listOfEmpDetails();
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < employees.size() - 1; i++) {
            try {
                //getting end of 1st shift
                String endDateStr = employees.get(i).getTimeIn();
                //getting start of 2nd shift
                String startDateStr = employees.get(i + 1).getTimeOut();
                // Check if date strings are not empty
                if (!endDateStr.isEmpty() && !startDateStr.isEmpty()) {
                    Date endTimeShift1 = DATE_FORMAT_WITH_TIME.parse(endDateStr);
                    Date startTimeShift2 = DATE_FORMAT_WITH_TIME.parse(startDateStr);
                    //calculating differences
                    long timeDifference = startTimeShift2.getTime() - endTimeShift1.getTime();
                    //converting sec to hour
                    long hoursDifference = timeDifference / (60 * 60 * 1000);
                    //checking minHour and maxHour
                    if (hoursDifference > minHours && hoursDifference < maxHours) {
                        result.add(List.of(employees.get(i).getId(), employees.get(i).getEmployeeName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // removing duplicates
        Set<List<String>> uniqueNames = new LinkedHashSet<>();
        uniqueNames.addAll(result);
        result = new ArrayList<>(uniqueNames);

        return result;
    }


}
