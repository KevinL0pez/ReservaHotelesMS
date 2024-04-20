package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Long idHotel;

    @NotNull(message = "The hotel name must not be null")
    @NotBlank(message = "The hotel name must not be blank")
    @Column(name = "name_hotel")
    private String nameHotel;

    @NotNull(message = "The hotel address must not be null")
    @NotBlank(message = "The hotel address must not be blank")
    @Column(name = "address_hotel")
    private String addressHotel;

    @NotNull(message = "The hotel description must not be null")
    @NotBlank(message = "The hotel description must not be blank")
    @Column(name = "description_hotel")
    private String descriptionHotel;

    // Quantity stars rating
    @NotNull(message = "The hotel classification must not be null")
    @Column(name = "classification_hotel")
    private Integer classificationHotel;

    @NotNull(message = "The hotel phone must not be null")
    @NotBlank(message = "The hotel phone must not be blank")
    @Column(name = "hotel_phone")
    private String hotelPhone;



    @Column(name = "hotel_active")
    private boolean hotelActive = true;

    @NotNull(message = "The hotel department must not be null")
    @ManyToOne
    @JoinColumn(name = "id_department")
    private DepartmentEntity department;

    @NotNull(message = "The hotel municipality must not be null")
    @ManyToOne
    @JoinColumn(name = "id_municipality")
    private MunicipalityEntity municipality;
}
