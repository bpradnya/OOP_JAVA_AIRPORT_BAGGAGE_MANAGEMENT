// Author: <name> | Member 2

public class ConveyorBelt {
    private final LuggageItem[] bags =
            new LuggageItem[LuggageItem.CONVEYOR_CAPACITY];

    private int count = 0;

    public synchronized void addBag(LuggageItem bag) {
        if (bag == null) {
            throw new IllegalArgumentException("Bag cannot be null");
        }

        if (count == bags.length) {
            throw new ConveyorFullException("Conveyor is full");
        }

        bags[count] = bag;
        count++;
    }

    public synchronized LuggageItem removeFirstBag() {
        if (count == 0) {
            return null;
        }

        LuggageItem firstBag = bags[0];

        for (int i = 0; i < count - 1; i++) {
            bags[i] = bags[i + 1];
        }

        count--;
        bags[count] = null;

        return firstBag;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized LuggageItem getBagAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                    "Invalid bag position"
            );
        }
        return bags[index];
    }

    public synchronized void displayAll() {
        if (count == 0) {
            System.out.println("Conveyor empty");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + bags[i].display());
        }
    }

    public void validateBag(LuggageItem bag, String gateFlight)
            throws BaggageWeightExceededException,
                   MisroutedLuggageException {
        if (bag == null) {
            throw new IllegalArgumentException("Bag cannot be null");
        }

        if (bag.getWeightKg() > LuggageItem.MAX_WEIGHT_KG) {
            throw new BaggageWeightExceededException(
                    "Bag " + bag.getTagNumber() + " is overweight"
            );
        }

        if (!bag.checkFlightMatch(gateFlight)) {
            throw new MisroutedLuggageException(
                    "Bag " + bag.getTagNumber()
                            + " is on the wrong flight"
            );
        }
    }
}