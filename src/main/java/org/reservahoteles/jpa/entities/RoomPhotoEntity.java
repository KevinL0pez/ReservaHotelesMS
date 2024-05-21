package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "RoomPhotos")
public class RoomPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_room_photo")
    private UUID idRoomPhoto;

    @Column(name = "photo_name")
    private String photoName;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "The room must not be null")
    @ManyToOne
    @JoinColumn(name = "id_room")
    private HotelRoomEntity room;
}
