package com.ApiBarco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_member;
    private String name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private String discharge_date;
    private int payment_amount;
    private String account_number;
    private boolean is_master;

    public Member() {
    }

    public Member(int id_member, String name, String last_name, String email, String phone, String address, String discharge_date, int payment_amount, String account_number, boolean is_master){
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.discharge_date = discharge_date;
        this.payment_amount = payment_amount;
        this.account_number = account_number;
        this.is_master = is_master;
    }


}
