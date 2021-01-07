package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.frontend;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Product;

import java.time.LocalDate;

public class AdvertisementInfo {
    private Product product;
    private LocalDate endDate;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
