/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;
import java.util.ArrayList;

/**
 *
 * @author BitCom Tech
 */
public class Customer {
    String name;
    String password;
    
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
