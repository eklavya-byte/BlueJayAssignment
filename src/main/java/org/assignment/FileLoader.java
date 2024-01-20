package org.assignment;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    static List<Employee> readDataFromCSV(String filePath) {
        List<Employee> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                String positionId = nextRecord[0];
                String positionStatus = nextRecord[1];
                String timeIn = nextRecord[2];
                String timeOut = nextRecord[3];
                String timecardHours = nextRecord[4];
                String payStartDate = nextRecord[5];
                String payEndDate = nextRecord[6];
                String employeeName = nextRecord[7];
                String fileNumber = nextRecord[8];

                data.add(new Employee(positionId, positionStatus, timeIn, timeOut, timecardHours, payStartDate, payEndDate, employeeName, fileNumber));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return data;
    }

}
