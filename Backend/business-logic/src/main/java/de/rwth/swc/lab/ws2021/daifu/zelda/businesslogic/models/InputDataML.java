package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

public class InputDataML {
    /*
        variable names and types DON'T rely on the data dictionary but on the training set (datasets_marketing_dataset.csv)
     */

    private Integer age;
    private String job;
    private String marital;
    private String education;
    private String delay; // variable name in given data is "default". However in java this is not possible as a variable name.
    private Integer balance;
    private String housing;
    private String loan;
    private Integer contact_day;
    private String contact_month;
    private Integer campaign;
    private Integer pdays;
    private Integer previous;
    private String poutcome;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public Integer getContact_day() {
        return contact_day;
    }

    public void setContact_day(Integer contact_day) {
        this.contact_day = contact_day;
    }

    public String getContact_month() {
        return contact_month;
    }

    public void setContact_month(String contact_month) {
        this.contact_month = contact_month;
    }

    public Integer getCampaign() {
        return campaign;
    }

    public void setCampaign(Integer campaign) {
        this.campaign = campaign;
    }

    public Integer getPdays() {
        return pdays;
    }

    public void setPdays(Integer pdays) {
        this.pdays = pdays;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public String getPoutcome() {
        return poutcome;
    }

    public void setPoutcome(String poutcome) {
        this.poutcome = poutcome;
    }
}
