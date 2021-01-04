package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.LoanRate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarLoan.class, name = "carLoan"),
        @JsonSubTypes.Type(value = de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.ConstructionLoan.class, name = "constructionLoan"),
        @JsonSubTypes.Type(value = de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Mortgage.class, name = "mortgage"),
        @JsonSubTypes.Type(value = de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.PrivateLoan.class, name = "privateLoan"),
        @JsonSubTypes.Type(value = de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.PropertyLoan.class, name = "propertyLoan")
})
@ApiModel(
        discriminator = "type",
        subTypes = {CarLoan.class, de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.ConstructionLoan.class,
                de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Mortgage.class, de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.PrivateLoan.class, de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.PropertyLoan.class}
)
public abstract class Loan {

    @ApiModelProperty(required = false, hidden = true)
    protected Integer id;

    @JsonBackReference(value = "customer-loans")
    protected Customer customer;

    @JsonManagedReference(value = "loan-loanRates")
    private Set<LoanRate> loanRates;

    @NonNull
    protected Double amount;

    @NonNull
    protected Double interest;

    @NonNull
    protected Double balance;

    @NonNull
    protected LoanStatus status;

    public enum LoanStatus {
        TIMELY("timely"),
        GRACE_PERIOD("grace period"),
        DEFAULT("default"),
        DEFICIT("deficit"),
        IRRECOVERABLE_DEBT("irrecoverable debt"),
        CLOSED("closed");

        @Getter
        private String stringRepresentation;

        private LoanStatus(String s) {
            this.stringRepresentation = s;
        }
    }

    public <T extends de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan> boolean isOfSameInstance(T otherLoan) {
        return (this.getClass().equals(otherLoan.getClass()));
    }
}
