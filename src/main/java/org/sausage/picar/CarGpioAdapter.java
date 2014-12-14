package org.sausage.picar;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by weenie on 07.12.14.
 */
@Component
public class CarGpioAdapter {

    private GpioPinDigitalOutput movePin;
    private GpioPinDigitalOutput moveDirectionPin;
    private GpioPinDigitalOutput turnPin;
    private GpioPinDigitalOutput turnDirectionPin;

    @Autowired
    public CarGpioAdapter(GpioPinDigitalOutput movePin, GpioPinDigitalOutput moveDirectionPin,
                          GpioPinDigitalOutput turnPin, GpioPinDigitalOutput turnDirectionPin) {
        this.movePin = movePin;
        this.moveDirectionPin = moveDirectionPin;
        this.turnPin = turnPin;
        this.turnDirectionPin = turnDirectionPin;
    }

    public void forward() {
        moveDirectionPin.setState(false);
        movePin.setState(true);
    }

    public void stop() {
        movePin.setState(false);
    }

    public void back() {
        moveDirectionPin.setState(true);
        movePin.setState(true);
    }

    public void left() {
        turnDirectionPin.setState(false);
        turnPin.setState(true);
    }

    public void right() {
        turnDirectionPin.setState(true);
        turnPin.setState(true);
    }

    public void straight() {
        turnPin.setState(false);
    }
}
