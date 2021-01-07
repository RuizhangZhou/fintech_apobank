package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Investment {

    @ApiModelProperty(required = false, hidden = true)
    private Integer id;

    @JsonBackReference(value = "customer-investments")
    private Customer customer;

    @NonNull
    private Double sum;

    @NonNull
    private Double interest;
}
