public class Coordinator extends Employee{
    private double mpf;
    
    public Coordinator(String staffID, String name, String schoolCode, double hSalary, int workHour, double salary, double totalSalary, String month, double mpf) {
    
        super(staffID, name, schoolCode, hSalary, workHour, salary, totalSalary, month);
        this.mpf = mpf;
    
    }
    
    public double getMPF() { return mpf; }
}
