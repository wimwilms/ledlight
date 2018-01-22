package be.discobolus.led;

import be.pixxis.lpd8806.LedStrip;

public class Lpd8806Strip implements Strip {
    private LedStrip ledstrip;

    public Lpd8806Strip(int numberOfLeds) {
        ledstrip = new LedStrip(numberOfLeds, 1);
    }

    @Override
    public void showSolidColor(int red, int green, int blue) {
        ledstrip.fill(red, green, blue);
    }
}
