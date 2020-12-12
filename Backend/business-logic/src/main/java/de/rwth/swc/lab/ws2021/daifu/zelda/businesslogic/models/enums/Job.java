package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Job {
    ADMIN("admin"),
    UNKNOWN("unknown"),
    UNEMPLOYED("unemployed"),
    MANAGEMENT("management"),
    HOUSEMAID("housemaid"),
    ENTREPRENEUR("entrepreneur"),
    STUDENT("student"),
    BLUE_COLLAR("blue-collar"),
    SELF_EMPLOYED("self-employed"),
    RETIRED("retired"),
    TECHNICIAN("technician"),
    SERVICES("services");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    private Job(String s) {
        this.stringRepresentation = s;
    }
}
