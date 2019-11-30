package net.ijiangtao.tech.ispringboot.demo.start.book.ijse9;

import java.util.ArrayList;

public class StaffService {

    public static void main(String[] args) {
        ArrayList<Manager> managers=new ArrayList<>();
        Manager m=new Manager();
        m.setName("aa");
        managers.add(m);

        printNames(managers);

        System.out.println(m.getName());
    }

    public static void printNames(ArrayList<? extends Employee> staff){

        //staff.removeAll();
        staff.clear();


        Manager m=new Manager();
        m.setName("aa");
        staff.remove(m);
       // staff.add(m);

        for (int i=0;i<staff.size();i++) {
            Employee e=staff.get(i);

            e.setName("bb");

           // Manager m=new Manager();
            //m.setName("cc");
           // staff.add(new Employee())


            System.out.println("="+e.getName());
        }
    }

}
