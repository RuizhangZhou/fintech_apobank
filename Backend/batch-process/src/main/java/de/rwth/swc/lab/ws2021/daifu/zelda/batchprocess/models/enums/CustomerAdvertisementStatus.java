package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum CustomerAdvertisementStatus {
    DISPLAYED("displayed"),
    DEACTIVATED("deactivated"),
    SUCCESS("success"),
    FAILURE("failure"),
    UNKNOWN("unknown");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    CustomerAdvertisementStatus(String s) {
        this.stringRepresentation = s;
    }
}
