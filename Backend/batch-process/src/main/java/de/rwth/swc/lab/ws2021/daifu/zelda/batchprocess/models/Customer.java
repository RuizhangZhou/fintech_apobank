package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.*;
import java.time.LocalDate;
import java.util.Set;


public class Customer {

    private Integer id;
    private Integer customerNumber;
    private Float monthlyIncome;
    private LocalDate birthday;
    private Integer numberOfChildren;
    private Job job;
    private RelationshipStatus relationshipStatus;
    private Education education;
    private Set<Account> accounts;
    private Set<Loan> loans;
    private Set<CustomerAdvertisement> customerAdvertisements;



    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
