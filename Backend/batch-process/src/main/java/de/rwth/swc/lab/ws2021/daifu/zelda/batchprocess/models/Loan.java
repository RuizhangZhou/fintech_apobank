package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.LoanStatus;
import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.LoanType;

public class Loan {

    private Integer id;
    private LoanStatus status;
    private LoanType type;

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }
}
