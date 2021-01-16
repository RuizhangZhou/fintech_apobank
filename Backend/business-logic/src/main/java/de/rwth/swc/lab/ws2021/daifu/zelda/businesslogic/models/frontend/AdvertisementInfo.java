package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.frontend;

import java.time.LocalDate;

public class AdvertisementInfo {
    private Integer advertisementCampaignId;
    private LocalDate advertisementCampaignStartDate;
    private LocalDate advertisementCampaignEndDate;
    private Integer productId;
    private String productDescription;
    private String productName;
    private String productType;

    public AdvertisementInfo(Integer advertisementCampaignId, LocalDate advertisementCampaignStartDate, LocalDate advertisementCampaignEndDate, Integer productId, String productDescription, String productName, String productType) {
        this.advertisementCampaignId = advertisementCampaignId;
        this.advertisementCampaignStartDate = advertisementCampaignStartDate;
        this.advertisementCampaignEndDate = advertisementCampaignEndDate;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productType = productType;
    }

    public Integer getAdvertisementCampaignId() {
        return advertisementCampaignId;
    }

    public void setAdvertisementCampaignId(Integer advertisementCampaignId) {
        this.advertisementCampaignId = advertisementCampaignId;
    }

    public LocalDate getAdvertisementCampaignStartDate() {
        return advertisementCampaignStartDate;
    }

    public void setAdvertisementCampaignStartDate(LocalDate advertisementCampaignStartDate) {
        this.advertisementCampaignStartDate = advertisementCampaignStartDate;
    }

    public LocalDate getAdvertisementCampaignEndDate() {
        return advertisementCampaignEndDate;
    }

    public void setAdvertisementCampaignEndDate(LocalDate advertisementCampaignEndDate) {
        this.advertisementCampaignEndDate = advertisementCampaignEndDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
