package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import java.util.Set;

public class ProductPage {
    private Set<Product> content;

    public Set<Product> getContent() {
        return content;
    }

    public void setContent(Set<Product> content) {
        this.content = content;
    }
}
