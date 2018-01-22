package be.discobolus.led;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        strip.showSolidColor(0,0,0);
    }

    @Async
    public void showSolidColor(int red, int green, int blue) {
        stopCurrentRun();
        strip.showSolidColor(red, green, blue);
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
                strip.showSolidColor(red, green, blue);
                green++;
                sleep();
            }

            for (green = 255; green > 0 && running; green--) {
                strip.showSolidColor(red, green, blue);
                blue++;
                sleep();
            }

            for (blue = 255; blue > 0 && running; blue--) {
                strip.showSolidColor(red, green, blue);
                red++;
                sleep();
            }
        }
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


}
