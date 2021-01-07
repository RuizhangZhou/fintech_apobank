package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(
        parent = de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan.class
)
public class CarLoan extends de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan {

}