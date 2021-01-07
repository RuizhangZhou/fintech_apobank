package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.enums;

public enum BooleanString {
    YES("yes"),
    NO("no");

    private String  stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    BooleanString(String s) {
        this.stringRepresentation = s;
    }
}
