package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums;

public enum LoanType {
    carLoan("carLoan"),
    constructionLoan("constructionLoan"),
    mortgage("mortgage"),
    privateLoan("privateLoan"),
    propertyLoan("propertyLoan");

    private String stringRepresentation;

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    LoanType(String s) {
        this.stringRepresentation = s;
    }
}
