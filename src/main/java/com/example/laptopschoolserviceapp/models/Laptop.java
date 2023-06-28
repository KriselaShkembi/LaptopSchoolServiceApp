package com.example.laptopschoolserviceapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double screenSize;

    private Integer RAM;

    private Integer batteryTime;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="laptop", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Ticket> tickets;

    // Constructors, Getters & Setters
    public Laptop(){
    }

    public Laptop(Long id, String description, Double screenSize, Integer RAM, Integer batteryTime, User user, List<Ticket> tickets) {
        this.id = id;
        this.description = description;
        this.screenSize = screenSize;
        this.RAM = RAM;
        this.batteryTime = batteryTime;
        this.user = user;
        this.tickets = tickets;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBatteryTime() {
        return batteryTime;
    }

    public void setBatteryTime(Integer batteryTime) {
        this.batteryTime = batteryTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public Integer getRAM() {
        return RAM;
    }

    public void setRAM(Integer RAM) {
        this.RAM = RAM;
    }

    public Integer getbatteryTime() {
        return batteryTime;
    }

    public void setbatteryTime(Integer batteryTime) {
        this.batteryTime = batteryTime;
    }
}
