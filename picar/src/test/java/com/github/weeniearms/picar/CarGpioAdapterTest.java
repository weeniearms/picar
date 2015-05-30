package com.github.weeniearms.picar;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

/**
 * Created by weenie on 07.12.14.
 */
@RunWith(Parameterized.class)
public class CarGpioAdapterTest {

    private final GpioPinDigitalOutput movePin = mock(GpioPinDigitalOutput.class);
    private final GpioPinDigitalOutput moveDirectionPin = mock(GpioPinDigitalOutput.class);
    private final GpioPinDigitalOutput turnPin = mock(GpioPinDigitalOutput.class);
    private final GpioPinDigitalOutput turnDirectionPin = mock(GpioPinDigitalOutput.class);
    private final Turn turn;
    private final Throttle throttle;
    private final Boolean expectedMoveState;
    private final Boolean expectedMoveDirectionState;
    private final Boolean expectedTurnState;
    private final Boolean expectedTurnDirectionState;

    public CarGpioAdapterTest(Turn turn, Throttle throttle, Boolean expectedMoveState,
                              Boolean expectedMoveDirectionState, Boolean expectedTurnState,
                              Boolean expectedTurnDirectionState) {
        this.turn = turn;
        this.throttle = throttle;
        this.expectedMoveState = expectedMoveState;
        this.expectedMoveDirectionState = expectedMoveDirectionState;
        this.expectedTurnState = expectedTurnState;
        this.expectedTurnDirectionState = expectedTurnDirectionState;
    }

    @Parameterized.Parameters
    public static Collection moves() {
        return Arrays.asList(new Object[][]{
                {null, Throttle.FORWARD, true, false, null, null},
                {null, Throttle.BACK, true, true, null, null},
                {null, Throttle.STOP, false, null, null, null},
                {Turn.LEFT, null, null, null, true, false},
                {Turn.RIGHT, null, null, null, true, true},
                {Turn.STRAIGHT, null, null, null, false, null}
        });
    }

    @Test
    public void shouldProperlyChangePinStates() {
        // Given
        CarGpioAdapter carGpioAdapter = new CarGpioAdapter(movePin, moveDirectionPin, turnPin, turnDirectionPin);

        // When
        if (turn != null) {
            carGpioAdapter.setTurn(turn);
        }

        if (throttle != null) {
            carGpioAdapter.setThrottle(throttle);
        }

        // Then
        verifyPinMock(movePin, expectedMoveState);
        verifyPinMock(moveDirectionPin, expectedMoveDirectionState);
        verifyPinMock(turnPin, expectedTurnState);
        verifyPinMock(turnDirectionPin, expectedTurnDirectionState);
    }

    private void verifyPinMock(GpioPinDigitalOutput pin, Boolean expectedPinState) {
        if (expectedPinState == null) {
            verify(pin, times(0)).setState(anyBoolean());
        } else {
            verify(pin).setState(expectedPinState);
        }
    }

}
