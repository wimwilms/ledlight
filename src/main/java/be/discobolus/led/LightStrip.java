package be.discobolus.led;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LightStrip {
    @Value("${led.nr.lights}")
    private int numberOfLeds;


    @Resource
    private Strip strip;

    private boolean running = false;

    @Async
    public void off() {
        stopCurrentRun();
        strip.showSolidColor(new Color(0,0,0));
    }

    @Async
    public void showSolidColor(int red, int green, int blue) {
        stopCurrentRun();
        strip.showSolidColor(new Color(red, green, blue));
    }

    @Async
    public void showRainbow() {
        stopCurrentRun();
        int red;
        int green = 0;
        int blue = 0;
        startNewRun();
        while(running) {
            for (red = 255; red > 0 && running; red--) {
                strip.showSolidColor(new Color(red, green, blue));
                green++;
                sleep();
            }

            for (green = 255; green > 0 && running; green--) {
                strip.showSolidColor(new Color(red, green, blue));
                blue++;
                sleep();
            }

            for (blue = 255; blue > 0 && running; blue--) {
                strip.showSolidColor(new Color(red, green, blue));
                red++;
                sleep();
            }
        }
    }

    @Async
    public void showDifferentSolidColors(List<Color> colors) {
        showDifferentColors(colors, (color, startLed, totalNumberOfLeds) -> color);
    }

    @Async
    public void showDifferentFadingColors(List<Color> colors) {
        showDifferentColors(colors, this::calculateBrightness);
    }

    private void showDifferentColors(List<Color> colors, TriFunction<Color, Integer, Integer, Color> calculateColor) {
        int numberOfColors = colors.size();
        if (numberOfColors > numberOfLeds) {
            throw new RuntimeException("To many colors");
        }

        int ledsWithoutClearColor = numberOfLeds % numberOfColors;

        int numberOfLedsForOneColor = numberOfLeds / numberOfColors;
        int totalLedNumber = 0;
        for (Color color : colors) {
            for (int i = 0; i < numberOfLedsForOneColor + (ledsWithoutClearColor > 0 ? 1 : 0); i++) {
                strip.showColor(totalLedNumber, calculateColor.apply(color, i, numberOfLedsForOneColor));
                totalLedNumber++;
            }
            ledsWithoutClearColor--;
        }
    }

    private Color calculateBrightness(Color color, int i, int totalOfLedsForColor) {
        double fadeGradient = ((double) totalOfLedsForColor - i) / totalOfLedsForColor;
        return new Color((int) (color.getRed() * fadeGradient), (int) (color.getGreen() * fadeGradient), (int) (color.getBlue() * fadeGradient));
    }


    public void stopCurrentRun() {
        if (running) {
            running = false;
            sleep();
        }
    }

    public void startNewRun() {
        if (running) {
            throw new RuntimeException("Already running");
        }
        running = true;
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @FunctionalInterface
    public interface TriFunction<T1,T2,T3,R>{
        public R apply(T1 t1,T2 t2,T3 t3);
    }
}
