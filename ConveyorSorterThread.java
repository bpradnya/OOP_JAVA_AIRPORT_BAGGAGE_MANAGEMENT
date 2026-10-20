public class ConveyorSorterThread extends Thread {
    private final ConveyorBelt belt;
    private final String gateFlight;
    private final FileHandler fileHandler;

    public ConveyorSorterThread(
            ConveyorBelt belt,
            String gateFlight,
            FileHandler fileHandler) {

        if (belt == null) {
            throw new IllegalArgumentException("Conveyor belt cannot be null");
        }

        if (fileHandler == null) {
            throw new IllegalArgumentException("FileHandler cannot be null");
        }

        this.belt = belt;
        this.gateFlight = gateFlight;
        this.fileHandler = fileHandler;
    }

    @Override
    public void run() {
        while (!belt.isEmpty()) {
            LuggageItem bag = belt.removeFirstBag();

            // The belt may have become empty before this bag was removed.
            if (bag == null) {
                break;
            }

            try {
                belt.validateBag(bag, gateFlight);

                System.out.println(
                        "Bag " + bag.getTagNumber()
                                + " routed to aircraft " + gateFlight
                );
                System.out.println(
                        "Instruction: " + bag.getHandlingInstruction()
                );
            }catch (BaggageWeightExceededException|MisroutedLuggageException e){
                System.out.println(e.getMessage());
                // Only the sorter logs flagged bags.
                fileHandler.logFlaggedBag(e.getMessage());
            } finally {
                System.out.println(
                        "Scan complete for " + bag.getTagNumber());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Conveyor interrupted");
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Conveyor finished");
    }
}