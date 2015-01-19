package org.sausage.picar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by weenie on 09.12.14.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {

    @Mock
    private MoveEventSource moveEventSource;

    @Mock
    private CarGpioAdapter carGpioAdapter;

    @InjectMocks
    private CarController carController;

    @Test
    public void shouldRegisterToMoveEventSourceOnInit() {
        // When
        carController.init();

        // Then
        verify(moveEventSource).addMoveEventListener(carController);
    }

    @Test
    public void shouldUnregisterFromMoveEventSourceOnTeardown() {
        // When
        carController.tearDown();

        // Then
        verify(moveEventSource).removeMoveEventListener(carController);
    }

    @Test
    public void shouldGoForward() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.FORWARD);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).forward();
        verifyNoMoreInteractions(carGpioAdapter);
    }

    @Test
    public void shouldGoBack() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.BACK);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).back();
        verifyNoMoreInteractions(carGpioAdapter);
    }

    @Test
    public void shouldStop() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.STOP);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).stop();
        verifyNoMoreInteractions(carGpioAdapter);
    }

    @Test
    public void shouldTurnLeft() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.LEFT);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).left();
        verifyNoMoreInteractions(carGpioAdapter);
    }

    @Test
    public void shouldTurnRight() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.RIGHT);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).right();
        verifyNoMoreInteractions(carGpioAdapter);
    }

    @Test
    public void shouldGoStraight() {
        // Given
        MoveEvent move = new MoveEvent(moveEventSource, MoveEvent.Move.STRAIGHT);

        // When
        carController.moveEventOccurred(move);

        // Then
        verify(carGpioAdapter).straight();
        verifyNoMoreInteractions(carGpioAdapter);
    }

}
