package com.bean;


import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-05-31 12:55
 */
public class Medicine {

    private Integer medId;
    private String  medName;
    private String efficacy;
    private String takingMethod;
    private Integer productDate;
    private Integer shelfLife;
    private BigDecimal price;
    private Integer quantity;
    private Boolean isSale;
    private String type;
    private String picUrl;
    private Boolean isUse;

    public Medicine() {
    }

    public Medicine(Integer medId, String medName, String efficacy, String takingMethod, Integer productDate, Integer shelfLife, BigDecimal price, Integer quantity, Boolean isSale, String type, String picUrl, Boolean isUse) {
        this.medId = medId;
        this.medName = medName;
        this.efficacy = efficacy;
        this.takingMethod = takingMethod;
        this.productDate = productDate;
        this.shelfLife = shelfLife;
        this.price = price;
        this.quantity = quantity;
        this.isSale = isSale;
        this.type = type;
        this.picUrl = picUrl;
        this.isUse = isUse;
    }

    public Medicine(String medName, String efficacy, String takingMethod, Integer productDate, Integer shelfLife, BigDecimal price, Integer quantity, Boolean isSale, String type, String picUrl, Boolean isUse) {
        this.medName = medName;
        this.efficacy = efficacy;
        this.takingMethod = takingMethod;
        this.productDate = productDate;
        this.shelfLife = shelfLife;
        this.price = price;
        this.quantity = quantity;
        this.isSale = isSale;
        this.type = type;
        this.picUrl = picUrl;
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medId=" + medId +
                ", medName='" + medName + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", takingMethod='" + takingMethod + '\'' +
                ", productDate=" + productDate +
                ", shelfLife=" + shelfLife +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isSale=" + isSale +
                ", type='" + type + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", isUse=" + isUse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(medId, medicine.medId) &&
                Objects.equals(medName, medicine.medName) &&
                Objects.equals(efficacy, medicine.efficacy) &&
                Objects.equals(takingMethod, medicine.takingMethod) &&
                Objects.equals(productDate, medicine.productDate) &&
                Objects.equals(shelfLife, medicine.shelfLife) &&
                Objects.equals(price, medicine.price) &&
                Objects.equals(quantity, medicine.quantity) &&
                Objects.equals(isSale, medicine.isSale) &&
                Objects.equals(type, medicine.type) &&
                Objects.equals(picUrl, medicine.picUrl) &&
                Objects.equals(isUse, medicine.isUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medId, medName, efficacy, takingMethod, productDate, shelfLife, price, quantity, isSale, type, picUrl, isUse);
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getTakingMethod() {
        return takingMethod;
    }

    public void setTakingMethod(String takingMethod) {
        this.takingMethod = takingMethod;
    }

    public Integer getProductDate() {
        return productDate;
    }

    public void setProductDate(Integer productDate) {
        this.productDate = productDate;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getSale() {
        return isSale;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }
}
