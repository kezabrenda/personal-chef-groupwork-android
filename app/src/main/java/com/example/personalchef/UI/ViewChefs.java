package com.example.personalchef.UI;

public class ViewChefs {
    public int imageId;
    public String names, foodSpecialty, hourlyRate, phone;

    public ViewChefs() {

    }

    public ViewChefs(String names, String foodSpecialty, String hourlyRate, int imageId, String phone) {
        this.names = names;
        this.foodSpecialty = foodSpecialty;
        this.hourlyRate = hourlyRate;
        this.imageId = imageId;
        this.phone = phone;
    }

    public int getImageId() {
        return imageId;
    }
    public String getFoodSpecialty() {
        return foodSpecialty;
    }
    public String getHourlyRate() {
        return hourlyRate;
    }
    public String getPhone() {
        return phone;
    }
}
