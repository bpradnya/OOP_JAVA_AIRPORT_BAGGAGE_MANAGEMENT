// Author: <name> | Member 1

public abstract class LuggageItem implements RFIDScannable {
    private String tagNumber;
    private String passengerId;
    private String flightNumber;
    private double weightKg;

    public static int totalBags = 0;
    public static final double MAX_WEIGHT_KG = 32.0;
    public static final int CONVEYOR_CAPACITY = 10;

    public LuggageItem(
            String tagNumber,
            String passengerId,
            double weightKg,
            String flightNumber) {
        setTagNumber(tagNumber);
        setPassengerId(passengerId);
        setWeightKg(weightKg);
        setFlightNumber(flightNumber);
        totalBags++;
    }

    public void setTagNumber(String tagNumber) {
        if (tagNumber == null || tagNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Tag number cannot be empty"
            );
        }
        this.tagNumber = tagNumber;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public void setWeightKg(double weightKg) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException(
                    "Weight must be greater than 0"
            );
        }
        this.weightKg = weightKg;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public abstract double calculateFee();

    public abstract String getHandlingInstruction();


    @Override
    public String readBarcode() {
        return tagNumber;
    }

    @Override
    public boolean checkFlightMatch(String gateFlight) {
        if (flightNumber == null || gateFlight == null) {
            return false;
        }
        return flightNumber.equalsIgnoreCase(gateFlight);
    }

    public String display() {
    return tagNumber + " | " + passengerId
            + " | " + weightKg + "kg | " + flightNumber;
}

    public static int getTotalBags() {
        return totalBags;
    }
}