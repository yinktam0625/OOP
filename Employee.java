

public abstract class Employee{
    protected String staffID;
    protected String name;
    protected int age;
    protected String gender;
    protected String mobileNo;
    protected String post;
    protected String address;
    protected int salary;
    protected int mpf;
    
    
    public Employee(String staffID, String name, int age, String gender, String mobileNo, String post, String address, int salary, int mpf ) {
        this.staffID = staffID;
        this.name    = name;
        this.age     = age;
        this.gender  = gender;
        this.mobileNo = mobileNo;
        this.post     = post;
        this.address  = address;
        this.mpf      = mpf;
        setSalary(salary);
    }
    
    public String getStaffID() {return staffID;}
    public String getName() {return name;}
    public String getGender() {return gender;}
    public int getAge() {return age;}
    public String getMobileNo() {return mobileNo;}
    public String getPost() {return post;}
    public String getAddress() {return address;}
    public int getSalary() {return salary;}
    public int getMPF() {return mpf;}
    
    public void setSalary(int salary) {this.salary = salary;}
    
    //check name
    
    public boolean isName(String nameCheck) {
        try{
            for (int i = 0; i < nameCheck.length(); i ++) {
                if (nameCheck.charAt(i) == '?') {
                    continue;
                }
                if(name.charAt(i) != nameCheck.charAt(i)){
                    return false;
                }
            }  
            } catch (Exception e) {
                    System.err.println("isNmae Exception:"+ e.getMessage());
                    return false;
            }
            return true;
        }
    
    
    //District
    public boolean isDistrict(String district){
        boolean result = false;
        try{
            String[] districts = address.split(",");
            String lDistrict = districts[districts.length - 2];
            if(lDistrict.equals(district)){
                result = true;
            }
            return result;
        }catch (Exception e){
            System.err.println("isDistrict Exception:"+ e.getMessage());
        }
        return result;
    }
    
    public abstract void increaseSalary(double percentage);
    
    public String toString(){
        return staffID +";"+ name+";"+ age+";" + gender+";" + mobileNo+";" + post+";" + address+";" + salary+";" + mpf;
    }
    
}






