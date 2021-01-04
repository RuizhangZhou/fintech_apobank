package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum Month {
    JAN("jan"),
    FEB("feb"),
    MAR("mar"),
    APR("apr"),
    MAY("may"),
    JUN("jun"),
    JUL("jul"),
    AUG("aug"),
    SEP("sep"),
    OCT("oct"),
    NOV("nov"),
    DEC("dec");

    private String  stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    Month(String s) {
        this.stringRepresentation = s;
    }
}
