public interface RFIDScannable {
    String readBarcode();
    boolean checkFlightMatch(String gateFlight);
}