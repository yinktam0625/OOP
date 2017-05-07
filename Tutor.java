public class Tutor extends Employee {
    private double bonus;
    
    public Tutor(String staffID, String name, String schoolCode, double hSalary, int workHour, double salary, double totalSalary, String month, double bonus) {
        super(staffID, name, schoolCode, hSalary, workHour, salary, totalSalary, month);
        this.bonus = bonus;
    }
    
    public double getBonus() {return bonus;}
    
}