package be.discobolus.led;

public class NotConnectedStrip implements Strip {
    public NotConnectedStrip(int numberOfLeds) {
    }

    @Override
    public void showSolidColor(int red, int green, int blue) {
        System.out.println("Red: " + red + ", Green: " + green + ", Blue: " + blue);
    }
}
