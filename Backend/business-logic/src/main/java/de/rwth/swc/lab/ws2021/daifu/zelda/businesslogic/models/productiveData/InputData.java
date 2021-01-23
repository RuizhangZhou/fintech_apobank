package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.enums.*;

public class InputData {
    /*
        variable names and types DON'T rely on the data dictionary but on the training set (datasets_marketing_dataset.csv)
     */

    private Integer ID;
    private Float age;
    private Job job;
    private MaritalStatus marital;
    private Education education;
    private BooleanString default_; // variable name in given data is "default". However in java this is not possible as a variable name.
    private Float balance;
    private BooleanString housing;
    private BooleanString loan;
    //private Contact contact;
    private Integer day;
    private Month month;
    private Float campaign;
    private Float pdays;
    private Integer previous;
    private Outcome poutcome;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public MaritalStatus getMarital() {
        return marital;
    }

    public void setMarital(MaritalStatus marital) {
        this.marital = marital;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public BooleanString getDefault_() {
        return default_;
    }

    public void setDefault_(BooleanString default_) {
        this.default_ = default_;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public BooleanString getHousing() {
        return housing;
    }

    public void setHousing(BooleanString housing) {
        this.housing = housing;
    }

    public BooleanString getLoan() {
        return loan;
    }

    public void setLoan(BooleanString loan) {
        this.loan = loan;
    }

    /*public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }*/

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Float getCampaign() {
        return campaign;
    }

    public void setCampaign(Float campaign) {
        this.campaign = campaign;
    }

    public Float getPdays() {
        return pdays;
    }

    public void setPdays(Float pdays) {
        this.pdays = pdays;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public Outcome getPoutcome() {
        return poutcome;
    }

    public void setPoutcome(Outcome poutcome) {
        this.poutcome = poutcome;
    }
}
