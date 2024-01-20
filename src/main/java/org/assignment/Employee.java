package org.assignment;

public class Employee {

    private String id;
    private String position;
    private String timeIn;
    private String timeOut;
    private String totalHours;
    private String payStartDate;
    private String payEndDate;
    private String employeeName;
    private String fileName;

    public Employee(String id, String position, String timeIn, String timeOut,String totalHours, String payStartDate, String payEndDate, String employeeName, String fileName) {
        this.id = id;
        this.position = position;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.totalHours = totalHours;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        this.employeeName = employeeName;
        this.fileName = fileName;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(String payStartDate) {
        this.payStartDate = payStartDate;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", timeIn='" + timeIn + '\'' +
                ", timeOut='" + timeOut + '\'' +
                ", totalHours='" + totalHours + '\'' +
                ", payStartDate='" + payStartDate + '\'' +
                ", payEndDate='" + payEndDate + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
