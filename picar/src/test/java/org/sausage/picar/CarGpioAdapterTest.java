package org.sausage.picar;

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
    private final TestAction action;
    private final Boolean expectedMoveState;
    private final Boolean expectedMoveDirectionState;
    private final Boolean expectedTurnState;
    private final Boolean expectedTurnDirectionState;

    public CarGpioAdapterTest(TestAction action, Boolean expectedMoveState, Boolean expectedMoveDirectionState,
                              Boolean expectedTurnState, Boolean expectedTurnDirectionState) {
        this.action = action;
        this.expectedMoveState = expectedMoveState;
        this.expectedMoveDirectionState = expectedMoveDirectionState;
        this.expectedTurnState = expectedTurnState;
        this.expectedTurnDirectionState = expectedTurnDirectionState;
    }

    @Parameterized.Parameters
    public static Collection moves() {
        return Arrays.asList(new Object[][]{
                {TestAction.FORWARD, true, false, null, null},
                {TestAction.BACK, true, true, null, null},
                {TestAction.STOP, false, null, null, null},
                {TestAction.LEFT, null, null, true, false},
                {TestAction.RIGHT, null, null, true, true},
                {TestAction.STRAIGHT, null, null, false, null}
        });
    }

    @Test
    public void shouldProperlyChangePinStates() {
        // Given
        CarGpioAdapter carGpioAdapter = new CarGpioAdapter(movePin, moveDirectionPin, turnPin, turnDirectionPin);

        // When
        switch (action) {
            case FORWARD:
                carGpioAdapter.forward();
                break;
            case BACK:
                carGpioAdapter.back();
                break;
            case STOP:
                carGpioAdapter.stop();
                break;
            case LEFT:
                carGpioAdapter.left();
                break;
            case RIGHT:
                carGpioAdapter.right();
                break;
            case STRAIGHT:
                carGpioAdapter.straight();
                break;
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

    public static enum TestAction {
        FORWARD, BACK, STOP, LEFT, RIGHT, STRAIGHT
    }

}
