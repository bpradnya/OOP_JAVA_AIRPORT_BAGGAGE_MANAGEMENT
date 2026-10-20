// Author: <name> | Member 3

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConveyorBelt conveyor = new ConveyorBelt();
        FileHandler fileHandler = new FileHandler();
        String gateFlight = "AI202";

        createSampleManifestFile();

        while (true) {
            System.out.println("\n===== AIRPORT BAGGAGE SYSTEM =====");
            System.out.println("1. Load bags from manifest file");
            System.out.println("2. Add a bag manually");
            System.out.println("3. Show all bags on conveyor");
            System.out.println("4. Start conveyor (sorter thread)");
            System.out.println("5. Show fee for a bag");
            System.out.println("6. View flagged bags");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            try {
                int choice =
                        Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        fileHandler.loadManifest(conveyor);
                        break;

                    case 2:
                        System.out.print(
                                "Enter type (STANDARD/PET): "
                        );
                        String type = scanner.nextLine().trim();

                        System.out.print("Enter tag number: ");
                        String tag = scanner.nextLine().trim();

                        System.out.print("Enter passenger ID: ");
                        String passenger = scanner.nextLine().trim();

                        System.out.print("Enter weight in kg: ");
                        double weight =
                                Double.parseDouble(
                                        scanner.nextLine().trim()
                                );

                        System.out.print("Enter flight number: ");
                        String flight = scanner.nextLine().trim();

                        LuggageItem bag = FileHandler.createBag(
                                type, tag, passenger, weight, flight
                        );

                        conveyor.addBag(bag);
                        System.out.println("Bag added successfully.");
                        break;

                    case 3:
                        conveyor.displayAll();
                        break;

                    case 4:
                        ConveyorSorterThread sorter =
                                new ConveyorSorterThread(
                                        conveyor, gateFlight, fileHandler
                                );
                        sorter.start();
                        sorter.join();
                        break;

                    case 5:
                        // System.out.print(
                        //         "Enter bag position (1 to "
                        //                 + conveyor.getCount() + "): "
                        // );
                        // int index =
                        //         Integer.parseInt(
                        //                 scanner.nextLine().trim()
                        //         ) - 1;

                        // if (index >= 0 && index < conveyor.getCount()) {
                        //     LuggageItem selectedBag =
                        //             conveyor.getBagAt(index);

                        //     System.out.print(
                        //             "Discount percentage (0 for none): "
                        //     );
                        //     double discount =
                        //             Double.parseDouble(
                        //                     scanner.nextLine().trim()
                        //             );

                        //     double fee = selectedBag.calculateFee();
                        //     double finalFee = discount > 0
                        //             ? selectedBag.calculateFee(discount)
                        //             : fee;

                        //     System.out.printf(
                        //             "Fee: %.2f | After discount: %.2f%n",
                        //             fee, finalFee
                        //     );
                        // } else {
                        //     System.out.println("Invalid bag position.");
                        // }
                        // break;

                       
                        System.out.print(
                                "Enter bag position (1 to " + conveyor.getCount() + "): "
                        );
                        int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (index >= 0 && index < conveyor.getCount()) {
                            LuggageItem selectedBag = conveyor.getBagAt(index);

                            System.out.printf(
                                    "Fee: %.2f%n",
                                    selectedBag.calculateFee()
                            );
                        } else {
                            System.out.println("Invalid bag position.");
                        }
                        break;

                    case 6:
                        fileHandler.viewFlaggedBags();
                        break;

                    case 7:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Choose a number from 1 to 7."
                        );
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createSampleManifestFile() {
        File file = new File("flight_baggage_manifest.txt");

        if (!file.exists()) {
            try (PrintWriter writer =
                         new PrintWriter(new FileWriter(file))) {
                writer.println("# TYPE,TAG,PASSENGER,WEIGHT,FLIGHT");
                writer.println("STANDARD,TAG101,P001,20.5,AI202");
                writer.println("PET,TAG102,P002,8,AI202");
                writer.println("COLD,TAG103,P003,15,AI202");
                writer.println("STANDARD,TAG104,P004,18,6E555");
            } catch (Exception e) {
                System.out.println(
                        "Could not create sample manifest: "
                                + e.getMessage()
                );
            }
        }
    }
}