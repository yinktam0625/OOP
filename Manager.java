

public class Manager extends Employee{
    private int houseAllow;
    private int travelAllow;
    
    public Manager(String staffID, String name, int age, String gender, String mobileNo, 
                    String post, String address, int salary, int mpf, int houseAllow, int travelAllow) {
    
        super(staffID, name, age, gender, mobileNo, post, address, salary, mpf);
        this.houseAllow = houseAllow;
        this.travelAllow = travelAllow;
    
    }
    
    public int getHouseAllow() {return houseAllow;}
    public int getTravelAllow() {return travelAllow;}
    
    public void increaseSalary(double percentage){
        double s=(double)salary;
        s = s * percentage; 
        salary = (int)s;
    }
    
    public String toString(){
        return super.toString()+";" + houseAllow+";" + travelAllow;
    }
}
