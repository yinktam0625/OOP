
public class IncreaseSalary {
    public static void main(String args[]){
        
        Employee [] man = new Employee[3];
        
        man[0] = new Manager("M000003", "CHIU Shun Kin", 33, "Male", "95515368", "HR Manager", "Room B,27/F,234 Chu Wing Street,Hung Hom,Kowloon", 115000, 5750, 11500, 11500);
        man[1]= new GeneralStaff("G000001", "AU Chi Shing", 22, "Male", "NA", "Engineer", "Flat A,15/F,332 Mei Yi Street,Kowloon Tong,Kowloon", 50000, 2500, 5000);
        man[2]= new Manager("M000001", "CHUNG Man Han", 55, "Male", "98340485", "General Manager", "Room D,16/F,37 Mei Yi Street,Kowloon Tong,Kowloon", 150000, 7500, 15000,15000);
        
     
        
        for (int i = 0 ;i < man.length ; i++){
            man[i].increaseSalary(1.1);
            System.out.println("sample record: "+ (i+1) + " " + man[i].toString());
        }   
       

    }
}