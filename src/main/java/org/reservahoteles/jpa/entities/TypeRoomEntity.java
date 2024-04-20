package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TypeRooms")
public class TypeRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_room")
    private Long idTypeRoom;

    @Column(name = "title_type_room")
    private String titleTypeRoom;

}
