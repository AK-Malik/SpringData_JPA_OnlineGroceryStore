package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity Manager Class
 */
@Entity
@Table(name="GROCERY_STORE")
public class OnlineGroceryStore {

    /**
     * id marks the field as the primary key.
     * GeneratedValue(strategy = GenerationType. IDENTITY) tells JPA to let the database automatically generate a unique value for this field (usually using an auto-increment column in databases like MySQL, PostgreSQL, SQL Server, etc.).
     */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)   //for auto generated value. this column don't need to add in JSON or manual insert
        @Column(name="SERIAL_NUMBER")
        private long serialNumber;

        @Column(name ="ITEM_NAME", length = 15, nullable = false)
        private String itemName;

        @Column(name="Item_INSERT_DT", length = 12)
        private LocalDate itemInsertDate;

        @Column(name="QUANTITY", length = 10)
        private Integer quantity;

        @Column(name ="BILL_AMOUNT", length = 10)
        private Double billAmount;

        @Column (name= "CUSTOMER_NAME", length = 15)
        private String customerName;

        @Column(name ="PHONE_NUMBER", length = 15)
        private String phoneNumber;

        @Column (name="E_MAIL", length = 30)
        private String email;

        @Column(name="Remarks")
        private String remarks;

    public long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getItemInsertDate() {
        return itemInsertDate;
    }
    public void setInsertDate(LocalDate itemInsertDate) {
        this.itemInsertDate = itemInsertDate;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBillAmount() {
        return billAmount;
    }
    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String toString(){
        return "GroceryStore: "+getSerialNumber() +", "+getItemName()+", "+getItemInsertDate()+", "+getQuantity()+", "+getBillAmount()+", "+getCustomerName()+", "+getPhoneNumber()+", "+getEmail()+", "+getRemarks();
    }

}




