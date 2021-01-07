package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@ApiModel(
        parent = Loan.class
)
public class PrivateLoan extends Loan {

    private String reason;

}