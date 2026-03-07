package com.example.demo.ticket;

import com.example.demo.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    protected Comment() {}

    public Comment(Long commentId, String content, Ticket ticket, User author) {
        this.commentId = commentId;
        this.content = content;
        this.ticket = ticket;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public void editContent(String newContent) {
        this.content = newContent;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public User getAuthor() {
        return author;
    }
}
