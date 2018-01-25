package be.discobolus.led;

public interface Strip {
    void showSolidColor(Color color);

    void showColor(int led, Color color);
}
