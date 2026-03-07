package com.example.demo.ticket;

import com.example.demo.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "ticket_id")
 private long ticketId;

 @Column(name = "title")
 private String title;

 @Column(name = "created_at")
 private LocalDateTime createdAt;

 @Column(name = "updated_at")
 private LocalDateTime updatedAt;

 @ManyToOne
 @JoinColumn(name = "created_by", nullable = false)
 private User createdBy; // user_id

 @ManyToOne
 @JoinColumn(name = "assignee")
 private User assignee; // user_id

 @ManyToOne
 @JoinColumn(name = "type_id", nullable = false)
 private TicketType type;

 @ManyToOne
 @JoinColumn(name = "status_id", nullable = false)
 private TicketStatus status;

 @ManyToOne
 @JoinColumn(name = "priority_id", nullable = false)
 private TicketPriority priority;

 protected Ticket() {}

 public Ticket(String title,
               User createdBy,
               TicketType type,
               TicketPriority priority,
               TicketStatus status) {

  this.title = title;
  this.createdBy = createdBy;
  this.type = type;
  this.priority = priority;
  this.status = status;
  this.createdAt = LocalDateTime.now();
 }

 public void assignTo(User assignee){
  this.assignee = assignee;
  this.updatedAt = LocalDateTime.now();
 }

 public void setStatus(TicketStatus newStatus){
  this.status = newStatus;
  this.updatedAt = LocalDateTime.now();
 }

 public String getTitle() {
  return title;
 }

 public LocalDateTime getCreatedAt() {
  return createdAt;
 }

 public LocalDateTime getUpdatedAt() {
  return updatedAt;
 }

 public User getCreatedBy() {
  return createdBy;
 }

 public User getAssignee() {
  return assignee;
 }

 public TicketType getType() {
  return type;
 }

 public TicketStatus getStatus() {
  return status;
 }

 public TicketPriority getPriority() {
  return priority;
 }
}
