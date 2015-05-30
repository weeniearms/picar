package com.github.weeniearms.picar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by weenie on 09.12.14.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarStateControllerTest {

    @Mock
    private CarGpioAdapter carGpioAdapter;

    private Turn turn = Turn.RIGHT;

    private Throttle throttle = Throttle.FORWARD;

    @InjectMocks
    private CarStateController carStateController;

    @Test
    public void shouldReturnCurrentStatus() {
        // Given
        when(carGpioAdapter.getThrottle()).thenReturn(throttle);
        when(carGpioAdapter.getTurn()).thenReturn(turn);


        // When
        CarState state = carStateController.current();

        // Then
        assertEquals(throttle, state.getThrottle());
        assertEquals(turn, state.getTurn());
    }

    @Test
    public void shouldUpdateGpioState() {
        // Given
        CarState state = new CarState(turn, throttle);

        // When
        carStateController.update(state);

        // Then
        ArgumentCaptor<Turn> turnCaptor = ArgumentCaptor.forClass(Turn.class);
        verify(carGpioAdapter).setTurn(turnCaptor.capture());
        assertEquals(turn, turnCaptor.getValue());

        ArgumentCaptor<Throttle> throttleCaptor = ArgumentCaptor.forClass(Throttle.class);
        verify(carGpioAdapter).setThrottle(throttleCaptor.capture());
        assertEquals(throttle, throttleCaptor.getValue());
    }

}

