package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.enums;

public enum MaritalStatus {
    MARRIED("married"),
    DIVORCED("divorced"),
    SINGLE("single");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    MaritalStatus(String s) {
        this.stringRepresentation = s;
    }
}
