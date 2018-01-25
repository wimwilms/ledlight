package be.discobolus.led;

public class NotConnectedStrip implements Strip {
    private int numberOfLeds;

    public NotConnectedStrip(int numberOfLeds) {
        this.numberOfLeds = numberOfLeds;
    }

    @Override
    public void showSolidColor(Color color) {
        System.out.println("Red: " + color.getRed() + ", Green: " + color.getGreen() + ", Blue: " + color.getBlue());
    }

    @Override
    public void showColor(int led, Color color) {
        if (led == 0 || led > numberOfLeds) {
            throw new RuntimeException("Led id is incorrect");
        }
        System.out.println("Led : " + led + ", Red: " + color.getRed() + ", Green: " + color.getGreen() + ", Blue: " + color.getBlue());
    }
}
