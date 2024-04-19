package org.reservahoteles.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TypeRoomDto {

    private Long idTypeRoom;

    private String titleTypeRoom;
}
