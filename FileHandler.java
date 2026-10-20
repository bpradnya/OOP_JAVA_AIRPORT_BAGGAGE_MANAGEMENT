// Author: <name> | Member 3

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {
    private static final String MANIFEST_FILE =
            "flight_baggage_manifest.txt";
    private static final String FLAGGED_FILE =
            "security_flagged_baggage.txt";

    public void loadManifest(ConveyorBelt conveyor) {
        File file = new File(MANIFEST_FILE);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");

                // Format: TYPE,TAG,PASSENGER,WEIGHT,FLIGHT
                if (parts.length != 5) {
                    System.out.println("Skipping bad line: " + line);
                    continue;
                }

                try {
                    String type = parts[0].trim();
                    String tag = parts[1].trim();
                    String passenger = parts[2].trim();
                    double weight =
                            Double.parseDouble(parts[3].trim());
                    String flight = parts[4].trim();

                    LuggageItem bag =
                            createBag(type, tag, passenger, weight, flight);

                    conveyor.addBag(bag);
                    System.out.println(
                            "Loaded bag " + tag + " from manifest."
                    );
                } catch (ConveyorFullException e) {
                    System.out.println(
                            "Conveyor is full. Stopping manifest load."
                    );
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Skipping bad line: " + line
                                    + " (" + e.getMessage() + ")"
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Manifest file '" + MANIFEST_FILE + "' not found."
            );
        }
    }

    public static LuggageItem createBag(
            String type,
            String tag,
            String passenger,
            double weight,
            String flight) {
        switch (type.trim().toUpperCase()) {
            case "STANDARD":
                return new StandardSuitcase(
                        tag, passenger, weight, flight
                );
            case "PET":
                return new PetCarrier(
                        tag, passenger, weight, flight
                );
            default:
                throw new IllegalArgumentException(
                        "Unknown bag type: " + type
                );
        }
    }

    public synchronized void logFlaggedBag(String message) {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FLAGGED_FILE, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.out.println(
                    "Error writing flagged file: " + e.getMessage()
            );
        }
    }

    public void viewFlaggedBags() {
        File file = new File(FLAGGED_FILE);

        if (!file.exists()) {
            System.out.println("No flagged bags yet.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read flagged bags file.");
        }
    }
}