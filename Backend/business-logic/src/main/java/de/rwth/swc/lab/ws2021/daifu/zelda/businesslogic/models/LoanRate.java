package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanRate {

    @ApiModelProperty(required = false, hidden = true)
    private Integer id;


    @JsonBackReference(value = "loan-loanRates")
    private Loan loan;

    private LocalDate dueDate;

    private Boolean payed;

    private LocalDate dateOfPayment;

    @NonNull
    private Double sum;

}
