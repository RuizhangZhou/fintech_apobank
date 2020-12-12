package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums;

public enum AdvertismentStatus {
    DEACTIVATED("deactivated"),
    DISPLAYED("displayed"),
    FAILURE("failure"),
    SUCCESS("success"),
    UNKNOWN("unknown");

    private String stringRepresentation;

    public String getStringRepresentation() {
            return stringRepresentation;
    }

    private AdvertismentStatus(String s) {
            this.stringRepresentation = s;
    }
}