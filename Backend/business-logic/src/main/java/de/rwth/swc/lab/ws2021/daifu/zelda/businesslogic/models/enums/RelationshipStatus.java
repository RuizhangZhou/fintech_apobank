package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums;

public enum RelationshipStatus {
    MARRIED("married"),
    REGISTERED_PARTNERSHIP("registered partnership"),
    DIVORCED("divorced"),
    SINGLE("single"),
    WIDOWED("widowed");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    private RelationshipStatus(String s) {
        this.stringRepresentation = s;
    }
}
