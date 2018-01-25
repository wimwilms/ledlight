package be.discobolus.led;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/led")
public class LedResource {
    @Resource
    private LightStrip lightStrip;


    @RequestMapping("/solid")
    public void showSolidColor(@RequestParam(value="red", defaultValue="0") int red,
                               @RequestParam(value="green", defaultValue="0")int green,
                               @RequestParam(value="blue", defaultValue="0") int blue) {
        lightStrip.showSolidColor(red, green, blue);
    }

    @RequestMapping("/solid/multiple")
    public void showSolidColors(@RequestBody List<Color> colors) {
        lightStrip.showDifferentSolidColors(colors);
    }

    @RequestMapping("/solid/multiple/fade")
    public void showSolidFadingColors(@RequestBody List<Color> colors) {
        lightStrip.showDifferentFadingColors(colors);
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
