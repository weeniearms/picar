package com.github.weeniearms.picar;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
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

    public Turn getTurn() {
        if (turnPin.getState().equals(PinState.LOW)) {
            return Turn.STRAIGHT;
        }

        return turnDirectionPin.getState().equals(PinState.LOW) ? Turn.LEFT : Turn.RIGHT;
    }

    public void setTurn(Turn turn) {
        LOG.info("Setting turn to {}", turn);

        switch (turn) {

            case LEFT:
                turnDirectionPin.setState(false);
                turnPin.setState(true);
                break;
            case STRAIGHT:
                turnPin.setState(false);
                break;
            case RIGHT:
                turnDirectionPin.setState(true);
                turnPin.setState(true);
                break;
        }
    }

    public Throttle getThrottle() {
        if (movePin.getState().equals(PinState.LOW)) {
            return Throttle.STOP;
        }

        return moveDirectionPin.getState().equals(PinState.LOW) ? Throttle.FORWARD : Throttle.BACK;
    }

    public void setThrottle(Throttle throttle) {
        LOG.info("Setting throttle to {}", throttle);

        switch (throttle) {

            case FORWARD:
                moveDirectionPin.setState(false);
                movePin.setState(true);
                break;
            case STOP:
                movePin.setState(false);
                break;
            case BACK:
                moveDirectionPin.setState(true);
                movePin.setState(true);
                break;
        }
    }

}
