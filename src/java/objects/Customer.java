/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;
import java.util.ArrayList;

/**
 *
 * @author Dag
 */
public class Customer {

    public Customer(String name, String city, String phone, String gender, String password, int age) { 
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.age = age;
    
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Customer(String name, String id, String password, String city, String phone, String gender, int age) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.age = age;
    }
    String name, id, city, phone, gender;
    String password;
    int age;
    
    public Customer(String name, String password){
        this.password = password;
        this.name = name;
    }
    @Override
    public String toString(){
        return "Custumer: "+this.name+"["+this.password+"]";
    }
    //for example purpose
    public static void main(String[] args) throws ClassNotFoundException {
        Customer c = new Customer("Jhon","123");
        ArrayList<Customer> arr = new ArrayList();
        arr.add(c);
        arr.add(c);
        System.out.println(arr);
    }
    
}
