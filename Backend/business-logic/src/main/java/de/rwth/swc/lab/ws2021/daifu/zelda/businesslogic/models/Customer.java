package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Education;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Job;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.RelationshipStatus;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.CostumerAdvertisment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Account;
import org.springframework.lang.NonNull;

import java.lang.annotation.Native;
import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private Integer id;
    private Integer customerNumber;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private Float monthlyIncome;

    private LocalDate birthday;

    private Set<CostumerAdvertisment> customerAdvertisments;

    private Integer numberOfChildren;

    @NonNull
    private Address address;

    @NonNull
    private Job job;

    @NonNull
    private RelationshipStatus relationshipStatus;

    @NonNull
    private Education education;
    private Set<Account> accounts;

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

    public Set<CostumerAdvertisment> getCustomerAdvertisments() {
        return customerAdvertisments;
    }

    public void setCustomerAdvertisments(Set<CostumerAdvertisment> customerAdvertisments) {
        this.customerAdvertisments = customerAdvertisments;
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
