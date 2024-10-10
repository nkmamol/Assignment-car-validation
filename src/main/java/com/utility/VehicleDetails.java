package com.utility;

public class VehicleDetails {
    private String registrationNumber;
    private String carBrand;
    private String carModel;
    private String carYear;

    public VehicleDetails(String registrationNumber, String carBrand, String carModel, String carYear) {
        this.registrationNumber = registrationNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carYear = carYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarYear() {
        return carYear;
    }

    public boolean equals(VehicleDetails etc) {
        return this.carBrand.equalsIgnoreCase(etc.getCarBrand()) &&
                this.carModel.equalsIgnoreCase(etc.getCarModel()) &&
                this.carYear.equalsIgnoreCase(etc.getCarYear());
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "Registration Number: " + registrationNumber +
                "Brand: " + carBrand +
                "Model: " + carModel +
                "Year: " + carYear +
                "}";
    }
}
