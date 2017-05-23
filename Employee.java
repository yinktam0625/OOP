public class Employee{
    protected String staffID;
    protected String name;
    protected String schoolCode;
    protected double hSalary;
    protected int workHour;
    protected double salary;
    protected double totalSalary;
    protected String month;
    
    
    public Employee(String staffID, String name, String schoolCode, double hSalary, int workHour, double salary, double totalSalary, String month) {
        this.staffID = staffID;
        this.name = name;
        this.schoolCode = schoolCode;
        this.hSalary  = hSalary;
        this.workHour = workHour;
        this.salary = salary;
        this.totalSalary  = totalSalary;
        this.month = month;
    }
    
    public String getStaffID() {return staffID;}
    public String getName() {return name;}
    public String getSchoolCode() {return schoolCode;}
    public double gethSalary() {return hSalary;}
    public int getWorkHour() {return workHour;}
    public double getSalary() {return salary;}
    public double getTotalSalary() { return totalSalary; }
    public String getMonth() { return month; }
    
    public String toString() { 
        return getStaffID();
    }
    
    
}