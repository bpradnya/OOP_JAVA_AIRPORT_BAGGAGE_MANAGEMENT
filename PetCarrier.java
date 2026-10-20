// Author: <name> | Member 1

public class PetCarrier extends LuggageItem {
    public PetCarrier(
            String tagNumber,
            String passengerId,
            double weightKg,
            String flightNumber) {
        super(tagNumber, passengerId, weightKg, flightNumber);
    }

    @Override
    public double calculateFee() {
        return 800.0;
    }

    @Override
    public String getHandlingInstruction() {
        return "Keep in pressurised hold";
    }

    @Override
    public String display() {
        return "[PET CARRIER] " + super.display();
    }
}