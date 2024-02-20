/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import objects.Customer;

/**
 *
 * @author DAGIM
 */
public class Admin  {
    protected String name, id, city, phone, gender;
    protected String password;
    protected int age;
    public Admin(String name, String city, String phone, String gender, String password, int age) {
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.age = age;
    }
    public Admin(String name, String id, String password, String city, String phone, String gender, int age) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.age = age;
    }
        @Override
    public String toString() {
        return "Admin{" + "name=" + name + ", id=" + id + ", city=" + city + ", phone=" + phone + ", gender=" + gender + ", password=" + password + ", age=" + age + '}';
    }
}
