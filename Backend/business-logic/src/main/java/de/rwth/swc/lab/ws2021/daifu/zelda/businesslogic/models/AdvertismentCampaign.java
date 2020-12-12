package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertismentCampaign {
    private Set<CostumerAdvertisment> costumerAdvertisments;
    private String endDate;
    private Product product;
    private String startDate;

    public Set<CostumerAdvertisment> getCostumerAdvertisments() {
        return costumerAdvertisments;
    }

    public void setCostumerAdvertisments(Set<CostumerAdvertisment> costumerAdvertisments) {
        this.costumerAdvertisments = costumerAdvertisments;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
