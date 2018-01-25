package be.discobolus.led;

public class NotConnectedStrip implements Strip {
    public NotConnectedStrip(int numberOfLeds) {
    }

    @Override
    public void showSolidColor(Color color) {
        System.out.println("Red: " + color.getRed() + ", Green: " + color.getGreen() + ", Blue: " + color.getBlue());
    }

    @Override
    public void showColor(int led, Color color) {
        System.out.println("Led : " + led + ", Red: " + color.getRed() + ", Green: " + color.getGreen() + ", Blue: " + color.getBlue());
    }
}
