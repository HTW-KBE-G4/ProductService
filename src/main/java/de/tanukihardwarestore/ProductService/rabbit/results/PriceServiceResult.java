package de.tanukihardwarestore.ProductService.rabbit.results;

import java.io.Serializable;

public class PriceServiceResult implements Serializable {

    private double total;

    public PriceServiceResult(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}
