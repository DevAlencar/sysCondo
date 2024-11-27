package org.sysCondo.model.userMessage;

import lombok.Getter;
import lombok.Setter;
import org.sysCondo.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "userMessage")
@Getter
@Setter
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userIdFrom", nullable = false)
    private User userFrom;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;
}
