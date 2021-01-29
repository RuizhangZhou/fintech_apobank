package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.enums;

public enum Outcome {
    SUCCESS("success"),
    FAILURE("failure"),
    OTHER("other"),
    UNKNOWN("unknown");

    private String  stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    Outcome(String s) {
        this.stringRepresentation = s;
    }
}
