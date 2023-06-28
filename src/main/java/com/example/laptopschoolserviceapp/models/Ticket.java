package com.example.laptopschoolserviceapp.models;

import com.example.laptopschoolserviceapp.enumerations.Status;

import javax.persistence.*;

@Entity
@Table (name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Status status;

    // Relationships
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="laptop_id")
    private Laptop laptop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="laptopPart_id")
    private LaptopPart laptopPart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    // Constructors, Getters & Setters
    public Ticket() {
    }

    public Ticket(Long id, String description, Status status, Laptop laptop, LaptopPart laptopPart, User user) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.laptop = laptop;
        this.laptopPart = laptopPart;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public LaptopPart getLaptopPart() {
        return laptopPart;
    }

    public void setLaptopPart(LaptopPart laptopPart) {
        this.laptopPart = laptopPart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
