package be.digitalcity.giuseppe.demospringwithalexandre.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime checkInHour;

    @Column(nullable = false)
    private LocalDateTime checkOutHour;

    private boolean isCancelled;

    private String reason;

    @ManyToOne
    private Tuteur tuteur;

    @ManyToOne
    private Enfant enfant;

    private int availability = 2;

}
