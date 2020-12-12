package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Set<AdvertismentCampaign> advertismentCampaigns;
    private String description;
    private Integer id;
    private String name;
    private String type;

    public Set<AdvertismentCampaign> getAdvertismentCampaigns() {
        return advertismentCampaigns;
    }

    public void setAdvertismentCampaigns(Set<AdvertismentCampaign> advertismentCampaigns) {
        this.advertismentCampaigns = advertismentCampaigns;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
