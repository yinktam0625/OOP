
public class GeneralStaff extends Employee {
    private int bonus;
    
    public GeneralStaff(String staffID, String name, int age, String gender, String mobileNo, String post, String address, int salary, int mpf, int bonus) {
        super(staffID, name, age, gender, mobileNo, post, address, salary, mpf);
        this.bonus = bonus;
    }
    
    public int getBonus() {return bonus;}
    
    public void increaseSalary(double percentage){  
        double s=(double)salary;
        s = s * percentage; 
        salary = (int)s;
    }
    
    public int getSalary() {return salary;}
    
    public String toString(){
       return super.toString()+";" + bonus;
    }
}
