// Author: <name> | Member 1

public class StandardSuitcase extends LuggageItem {
    public StandardSuitcase(
            String tagNumber,
            String passengerId,
            double weightKg,
            String flightNumber) {
        super(tagNumber, passengerId, weightKg, flightNumber);
    }

    @Override
    public double calculateFee() {
        return 100.0;
    }

    @Override
    public String getHandlingInstruction() {
        return "Send to normal belt";
    }

    @Override
    public String display() {
        return "[STANDARD] " + super.display();
    }
}