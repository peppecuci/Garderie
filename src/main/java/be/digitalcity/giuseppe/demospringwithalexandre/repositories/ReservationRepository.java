package be.digitalcity.giuseppe.demospringwithalexandre.repositories;

import be.digitalcity.giuseppe.demospringwithalexandre.model.dto.ReservationDTO;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Enfant;
import be.digitalcity.giuseppe.demospringwithalexandre.model.entities.Reservation;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.checkInHour BETWEEN ?1 AND ?2 AND r.checkOutHour BETWEEN ?1 AND ?2 ")
    List<Reservation> getAllReservationByDate(LocalDateTime dateBegin, LocalDateTime dateEnd);

    @Query("SELECT r.enfant FROM Reservation r WHERE r.checkInHour BETWEEN ?1 AND ?2 AND r.checkOutHour BETWEEN ?1 AND ?2")
    List<Enfant> getAllEnfantByDate(LocalDateTime dateBegin, LocalDateTime dateEnd);

    @Query("SELECT r FROM Reservation r WHERE r.checkInHour > CURRENT_DATE AND r.enfant.id = ?1")
    List<Reservation> getAllByCheckInHourAfter(Long id);

    @Query("SELECT r FROM Reservation r WHERE MONTH(CURRENT_DATE) = MONTH(r.checkInHour) AND r.checkInHour >= current_date AND r.isCancelled = false")
    List<Reservation> findReservationsByCurrentMonth();


}
