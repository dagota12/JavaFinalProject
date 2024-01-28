/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

/**
 *
 * @author BitCom Tech
 */
public class Product {

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public String name;
    public double price;
    public String description;
    public int id;

    public Product(String name, double price, String description,int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
        
        
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + '}';
    }
    public static void main(String[] args) {
        Product p = new Product("a",4,"jhk",3);
        System.out.println(p.id);
    }


    
    
}
