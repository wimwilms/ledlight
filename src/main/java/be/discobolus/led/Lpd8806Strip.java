package be.discobolus.led;

import be.pixxis.lpd8806.LedStrip;

public class Lpd8806Strip implements Strip {
    private LedStrip ledstrip;

    public Lpd8806Strip(int numberOfLeds) {
        ledstrip = new LedStrip(numberOfLeds, 1);
    }

    @Override
    public void showSolidColor(Color color) {
        ledstrip.fill(color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public void showColor(int led, Color color) {
        ledstrip.setLed(led, color.getRed(), color.getGreen(), color.getBlue());
    }
}
