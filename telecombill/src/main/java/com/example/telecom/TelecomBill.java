package com.example.telecom;

public class TelecomBill {

    public static boolean validateConnectionType(String connectionType) {
        return connectionType.equalsIgnoreCase("Prepaid")
                || connectionType.equalsIgnoreCase("Postpaid");
    }

    public static double calculateBillAmount(int dataUnits) {
        double amount = 0.0;

        if (dataUnits > 0) {
            if (dataUnits < 100) {
                amount = dataUnits * 1.50;
            } else if (dataUnits >= 100 && dataUnits < 500) {
                amount = dataUnits * 2.00;
            } else if (dataUnits >= 500) {
                amount = dataUnits * 3.50;
            }
        }

        return amount;
    }
}
