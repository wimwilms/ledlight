package be.discobolus.led;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/led")
public class LedResource {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Resource
    private LightStrip lightStrip;


    @RequestMapping("/solid")
    public void showSolidColor(@RequestParam(value="red", defaultValue="0") int red,
                               @RequestParam(value="green", defaultValue="0")int green,
                               @RequestParam(value="blue", defaultValue="0") int blue) {
        lightStrip.showSolidColor(red, green, blue);
    }

    @RequestMapping("/rainbow")
    public void showRainbow() {
        lightStrip.showRainbow();
    }

    @RequestMapping("/off")
    public void off() {
        lightStrip.off();
    }
}
