package com.example.demo.ticket;

import jakarta.persistence.*;

@Entity
public class TicketPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id")
    private long priorityId;

    @Column(name = "priority_name")
    private  String name;

    public String getName() {
        return name;
    }
}
