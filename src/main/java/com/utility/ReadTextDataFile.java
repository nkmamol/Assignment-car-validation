package com.utility;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTextDataFile {

    private static final String carRegistrationNoRegex = "[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}";

    public List<String> readTextDataFile(String filePath) throws IOException {
        List<String> carNumbers = new ArrayList<>();
        try {
            List<String> fileData = Files.readAllLines(Paths.get(filePath));
            Pattern carNumbersPattern = Pattern.compile(carRegistrationNoRegex);
            for (String data : fileData) {
                Matcher match = carNumbersPattern.matcher(data);
                while (match.find()) {
                    carNumbers.add(match.group());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("carNumbers=====" + carNumbers);
        return carNumbers;
    }

    public Map<String, VehicleDetails> readOutputTextDataFile(String filePath) throws IOException {
        Map<String, VehicleDetails> expectedVehicleDetails = new HashMap<>();
        try {
            List<String> fileData = Files.readAllLines(Paths.get(filePath));
            for (String outputData : fileData) {
                if (!outputData.startsWith("VARIANT_REG")) {
                    String registrationNumber = outputData.split(",")[0].trim();
                    String carBrand = outputData.split(",")[1].trim();
                    String carModel = outputData.split(",")[2].trim();
                    String carYear = outputData.split(",")[3].trim();
                    expectedVehicleDetails.put(registrationNumber, new VehicleDetails(registrationNumber, carBrand, carModel, carYear));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expectedVehicleDetails;
    }

    public boolean compareDetailsVehicle(VehicleDetails exp, VehicleDetails act) {
        return exp.equals(act);
    }
}


