package com.example.demo.ticket;

import jakarta.persistence.*;
import org.springframework.core.SpringVersion;

@Entity
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long typeId;

    @Column(name = "type_name")
    private  String name;

    protected  TicketType(){}

    public TicketType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
