package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "HotelPhotos")
public class HotelPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel_photo")
    private Long idHotelPhoto;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "active")
    private boolean active = true;

    @NotNull(message = "The hotel must not be null")
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private HotelEntity hotel;
}
