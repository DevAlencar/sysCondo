package org.sysCondo.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Table(name = "statement")
@Getter
@Setter
public class Statement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long statementId;

        @Column(name = "statementTitle", nullable = false)
        private String statementTitle;

        @Column(name = "statementDescription", nullable = false)
        private String statementDescription;

        @Column(name = "statementDate", nullable = false)
        private LocalDate statementDate;

        @Column(name = "statementStatus", nullable = false)
        private String statementStatus;

        @Override
        public String toString() {
            return "Título: " + statementTitle + " | Descrição: " + statementDescription + " | Data: " + statementDate + " | Status: " + statementStatus;
        }
}
