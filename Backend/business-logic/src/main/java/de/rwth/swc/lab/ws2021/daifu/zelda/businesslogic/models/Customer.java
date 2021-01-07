package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Education;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Job;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.RelationshipStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts.Account;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private Integer id;
    private Integer customerNumber;

    private String firstName;


    private String lastName;

    private Float monthlyIncome;

    @ApiModelProperty(notes = "Birthday in format yyyy-MM-dd")
    private LocalDate birthday;

    private Set<CustomerAdvertisement> customerAdvertisements;

    private Integer numberOfChildren;

    private Address address;

    private Job job;

    private RelationshipStatus relationshipStatus;

    private Education education;

    @JsonManagedReference(value = "customer-accounts")
    @ApiModelProperty(example = "[ {'id': 1} ]")
    private Set<Account> accounts;

    @JsonManagedReference(value = "customer-loans")
    @ApiModelProperty(example = "[ {'id': 1} ]")
    private Set<Loan> loans;

    @JsonManagedReference(value = "customer-investments")
    @ApiModelProperty(example = "[ {'id': 1} ]")
    private Set<Investment> investments;

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
        this.investments = investments;
    }

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<CustomerAdvertisement> getCustomerAdvertisements() {
        return customerAdvertisements;
    }

    public void setCustomerAdvertisements(Set<CustomerAdvertisement> customerAdvertisements) {
        this.customerAdvertisements = customerAdvertisements;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }
}
