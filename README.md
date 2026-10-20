# Airport Baggage Management System

## About the Project

The Airport Baggage Management System is a Java program that simulates how bags are handled at an airport. Users can load bags from a manifest file or add them manually. The program places bags on a conveyor, checks their weight and flight number, and processes them one at a time.

## Features

- Load bags from a manifest text file.
- Add a bag by entering its details.
- Display bags currently on the conveyor.
- Process bags one at a time using a sorter thread.
- Check whether a bag is over the 32 kg weight limit.
- Check whether a bag’s flight number matches the gate flight.
- Record overweight or incorrectly routed bags in a security file.
- Show the fee and handling instructions for supported bag types.
- Store up to 10 bags on the conveyor.

## Supported Bag Types

- **Standard suitcase** — sent to the normal belt. Fee: 100.
- **Pet carrier** — kept in the pressurised hold. Fee: 800.

## Requirements

- Java Development Kit (JDK)

## How to Run

1. Download or clone this repository.
2. Open a terminal in the folder containing the Java files.
3. Compile the Java files:

   ```bash
   javac *.java
4. Run the program:
   java Main
Manifest File
The program reads bag details from flight_baggage_manifest.txt. Each line should contain one bag, with the fields in this order:
TYPE,TAG_NUMBER,PASSENGER_ID,WEIGHT_KG,FLIGHT_NUMBER
Example:
STANDARD,TAG101,P001,20.5,AI202
PET,TAG102,P002,8,AI202
Menu Options
1. Load bags from the manifest file.
2. Add a bag manually.
3. Show all bags on the conveyor.
4. Start the conveyor sorter.
5. Show the fee for a bag.
6. View flagged bags.
7. Exit the program.
Project Files
- Main.java — displays the menu and connects the program features.
- LuggageItem.java — stores details shared by all bags.
- StandardSuitcase.java — defines the standard suitcase.
- PetCarrier.java — defines the pet carrier.
- RFIDScannable.java — defines barcode and flight-check methods.
- ConveyorBelt.java — stores, displays, and checks bags.
- ConveyorSorterThread.java — processes bags one at a time.
- FileHandler.java — reads the manifest and records flagged bags.
- Exception files — define errors for an overweight bag, a wrong-flight bag, or a full conveyor.
Team Members
- Member 1: Pradnya Banshelkikar
- Member 2: Sharvari Deshmukh
- Member 3: Shravani Jadhav
