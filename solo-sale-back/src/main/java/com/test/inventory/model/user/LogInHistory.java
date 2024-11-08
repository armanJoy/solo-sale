package com.test.inventory.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login_history", indexes = {
        @Index(name = "in_login_history_user_id", columnList = "login_by")
})
public class LogInHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "login_by", foreignKey = @ForeignKey(name = "fk_login_history_user_id"))
    private User loginBy;

    private String userIP;

    @Column(name = "user_agent")
    private String userAgent;
}