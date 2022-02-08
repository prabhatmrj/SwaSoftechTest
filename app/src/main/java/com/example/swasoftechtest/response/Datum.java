
package com.example.swasoftechtest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sub_category")
    @Expose
    private String subCategory;
    @SerializedName("vendorId")
    @Expose
    private String vendorId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cateName")
    @Expose
    private String cateName;
    @SerializedName("vendor_name")
    @Expose
    private String vendor_name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }



    /**
     * 
     * @param image
     * @param createdAt
     * @param id
     * @param status
     * @param updatedAt
     */
    public Datum(Integer id, String image, String status, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.image = image;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getCateName() {
        return cateName;
    }


    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
