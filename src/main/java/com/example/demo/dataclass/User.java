package com.example.demo.dataclass;

import jakarta.persistence.*;

import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"phone"})
        }
)
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String fio;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String phone;
    private String address;
    private String sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}