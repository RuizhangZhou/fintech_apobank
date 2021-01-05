package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum Contact {
    CELLULAR("cellular"),
    TELEPHONE("telephone");

    private String  stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    Contact(String s) {
        this.stringRepresentation = s;
    }
}
