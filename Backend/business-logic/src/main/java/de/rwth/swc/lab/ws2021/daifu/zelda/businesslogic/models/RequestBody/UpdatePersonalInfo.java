package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Education;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Job;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.RelationshipStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePersonalInfo {
    private Education education;
    private Job job;
    private Float monthlyIncome;
    private RelationshipStatus relationshipStatus;
    private Integer numberOfChildren;

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
