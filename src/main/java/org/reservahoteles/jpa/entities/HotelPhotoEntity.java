package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "HotelPhotos")
public class HotelPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_hotel_photo")
    private UUID idHotelPhoto;

    @Column(name = "photo_name")
    private String photoName;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "The hotel must not be null")
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private HotelEntity hotel;
}
