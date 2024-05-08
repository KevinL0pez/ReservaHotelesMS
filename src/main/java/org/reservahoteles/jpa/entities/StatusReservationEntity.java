package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "StatusReservation")
public class StatusReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_reservation")
    private Long idStatusReservation;

    @Column(name = "title_status_reservation")
    private String titleStatusReservation;
}
