package be.discobolus.led;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StripConfiguration {
    @Value("${led.nr.lights}")
    private int numberOfLeds;

    @Bean
    @Profile("lpd8806")
    public Strip getLpd8806Strip() {
        return new Lpd8806Strip(numberOfLeds);
    }

    @Bean
    @Profile("default")
    public Strip getNotConnectedStrip() {
        return new NotConnectedStrip(numberOfLeds);
    }
}
