package org.reservahoteles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRequestDto {

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
    @NotNull
    private Integer classificationHotel;

    private boolean hotelActive = true;

    @NotNull
    private Long idDepartment;

    @NotNull
    private Long idMunicipality;
}