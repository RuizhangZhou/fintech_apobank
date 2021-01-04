package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum Education {
    PRIMARY("primary"),
    SECONDARY("secondary"),
    TERTIARY("tertiary"),
    UNKNOWN("unknown");

    private String  stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    Education(String s) {
        this.stringRepresentation = s;
    }
}
