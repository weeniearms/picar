package org.sausage.picar;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by weenie on 07.12.14.
 */
@Component
public class CarGpioAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(CarGpioAdapter.class);
    private final GpioPinDigitalOutput movePin;
    private final GpioPinDigitalOutput moveDirectionPin;
    private final GpioPinDigitalOutput turnPin;
    private final GpioPinDigitalOutput turnDirectionPin;

    @Autowired
    public CarGpioAdapter(GpioPinDigitalOutput movePin, GpioPinDigitalOutput moveDirectionPin,
                          GpioPinDigitalOutput turnPin, GpioPinDigitalOutput turnDirectionPin) {
        this.movePin = movePin;
        this.moveDirectionPin = moveDirectionPin;
        this.turnPin = turnPin;
        this.turnDirectionPin = turnDirectionPin;
    }

    public void forward() {
        LOG.info("Setting move forward pins.");
        moveDirectionPin.setState(false);
        movePin.setState(true);
    }

    public void stop() {
        LOG.info("Setting stop pins.");
        movePin.setState(false);
    }

    public void back() {
        LOG.info("Setting move back pins.");
        moveDirectionPin.setState(true);
        movePin.setState(true);
    }

    public void left() {
        LOG.info("Setting turn left pins.");
        turnDirectionPin.setState(false);
        turnPin.setState(true);
    }

    public void right() {
        LOG.info("Setting turn right pins.");
        turnDirectionPin.setState(true);
        turnPin.setState(true);
    }

    public void straight() {
        LOG.info("Setting straight pins.");
        turnPin.setState(false);
    }
}
