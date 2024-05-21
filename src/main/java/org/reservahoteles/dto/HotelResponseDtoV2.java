package org.reservahoteles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.DepartmentEntity;
import org.reservahoteles.jpa.entities.MunicipalityEntity;

@Data
public class HotelResponseDtoV2 {

    private Long idHotel;

    @NotBlank(message = "The hotel name must not be blank")
    @NotNull
    private String nameHotel;

    @NotBlank(message = "The hotel address must not be blank")
    @NotNull
    private String addressHotel;

    @NotBlank(message = "The hotel description must not be blank")
    @NotNull
    private String descriptionHotel;

    @NotNull
    @NotBlank(message = "The hotel phone must not be blank")
    private String hotelPhone;

    // Quantity stars rating
    @NotBlank(message = "The hotel classification must not be blank")
    @NotNull
    private Integer classificationHotel;

    private boolean hotelActive;

    @NotBlank(message = "The hotel department must not be blank")
    @NotNull
    private DepartmentDto department;

    @NotBlank(message = "The hotel municipality must not be blank")
    @NotNull
    private MunicipalityDto municipality;
}
