package com.bbirincioglu.amazon.store.utilities.model;

import lombok.Data;

@Data
public class ProductInfo {
    private String id;
    private double cost;
    private int count;
    private int monthlySoldCount;
    private String asinNumber;
    private double amazonPrice;
    private double weight;
    private boolean octoberDecember;
}
