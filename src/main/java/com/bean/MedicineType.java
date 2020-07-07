package com.bean;

import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-05-31 13:01
 */
public class MedicineType {
    private String type;
    private String typeName;
    private boolean isUse;

    public MedicineType() {
    }

    public MedicineType(String type, String typeName, boolean isUse) {
        this.type = type;
        this.typeName = typeName;
        this.isUse = isUse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineType that = (MedicineType) o;
        return isUse == that.isUse &&
                Objects.equals(type, that.type) &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, typeName, isUse);
    }

    @Override
    public String toString() {
        return "MedicineType{" +
                "type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", isUse=" + isUse +
                '}';
    }
}
