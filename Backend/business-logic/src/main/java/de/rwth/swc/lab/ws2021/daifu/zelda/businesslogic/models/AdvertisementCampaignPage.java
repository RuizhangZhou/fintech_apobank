package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementCampaignPage {
    private List<AdvertisementCampaign> content;

    public List<AdvertisementCampaign> getContent() {
        return content;
    }

    public void setContent(List<AdvertisementCampaign> content) {
        this.content = content;
    }
}
