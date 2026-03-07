package com.example.demo.ticket;

import jakarta.persistence.*;

@Entity
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private long statusId;

    @Column(name = "status_name")
    private  String name;

    protected  TicketStatus(){}

    public TicketStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
