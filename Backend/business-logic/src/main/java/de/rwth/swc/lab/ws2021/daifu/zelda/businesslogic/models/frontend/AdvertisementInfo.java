package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.frontend;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Product;

import java.time.LocalDate;

public class AdvertisementInfo {
    private Integer advertisementCampaignId;
    private LocalDate startDate;
    private LocalDate endDate;

    public AdvertisementInfo(Integer advertisementCampaignId, LocalDate startDate, LocalDate endDate) {
        this.advertisementCampaignId = advertisementCampaignId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getAdvertisementCampaignId() {
        return advertisementCampaignId;
    }

    public void setAdvertisementCampaignId(Integer advertisementCampaignId) {
        this.advertisementCampaignId = advertisementCampaignId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
