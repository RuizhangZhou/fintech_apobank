package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum LoanStatus {
    TIMELY("timely"),
    GRACE_PERIOD("grace period"),
    DEFAULT("default"),
    DEFICIT("deficit"),
    IRRECOVERABLE_DEBT("irrecoverable debt"),
    CLOSED("closed");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    LoanStatus(String s) {
        this.stringRepresentation = s;
    }
}
