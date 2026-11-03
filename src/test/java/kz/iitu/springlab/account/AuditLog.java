package kz.iitu.springlab.account;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog() {}

    public AuditLog(String action) {
        this.action = action;
    }

    public Long getId() { return id; }
    public String getAction() { return action; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}