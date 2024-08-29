package com.example.order.model;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class  Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;


    @Column(name = "user_id")
    private String user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String email_id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "product")
    private String product;

    
    @Column(name = "quantity")
    private int quantity;




public Order() {
    
}
public Order(String user_id, String name,String email_id,String phone,String product,int quantity){
    this.user_id=user_id;
    this.name=name;
    this.email_id=email_id;
    this.phone=phone;
    this.product=product;
    this.quantity=quantity;
}
public String getUser_id() {
    return user_id;
}
public int getOrder_id() {
    return order_id;
}
public void setOrder_id(int order_id) {
    this.order_id = order_id;
}
public void setUser_id(String user_id) {
    this.user_id = user_id;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getEmail_id() {
    return email_id;
}
public void setEmail_id(String email_id) {
    this.email_id = email_id;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}
public String getProduct() {
    return product;
}
public void setProduct(String product) {
    this.product = product;
}
public int getQuantity() {
    return quantity;
}
public void setQuantity(int quantity) {
    this.quantity = quantity;
}
@Override
public String toString() {
    return "Order [order_id=" + order_id + ", user_id=" + user_id + ", name=" + name + ", email_id=" + email_id
            + ", phone=" + phone + ", product=" + product + ", quantity=" + quantity + "]";
}

}