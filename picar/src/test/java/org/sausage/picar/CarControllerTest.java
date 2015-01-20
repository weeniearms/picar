package org.sausage.picar;

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
public class CarControllerTest {

    @Mock
    private CarGpioAdapter carGpioAdapter;

    private Turn turn = Turn.RIGHT;

    private Throttle throttle = Throttle.FORWARD;

    @InjectMocks
    private CarController carController;

    @Test
    public void shouldReturnCurrentStatus() {
        // Given
        when(carGpioAdapter.getThrottle()).thenReturn(throttle);
        when(carGpioAdapter.getTurn()).thenReturn(turn);


        // When
        Car state = carController.state();

        // Then
        assertEquals(throttle, state.getThrottle());
        assertEquals(turn, state.getTurn());
    }

    @Test
    public void shouldUpdateGpioState() {
        // Given
        Car state = new Car(turn, throttle);

        // When
        carController.update(state);

        // Then
        ArgumentCaptor<Turn> turnCaptor = ArgumentCaptor.forClass(Turn.class);
        verify(carGpioAdapter).setTurn(turnCaptor.capture());
        assertEquals(turn, turnCaptor.getValue());

        ArgumentCaptor<Throttle> throttleCaptor = ArgumentCaptor.forClass(Throttle.class);
        verify(carGpioAdapter).setThrottle(throttleCaptor.capture());
        assertEquals(throttle, throttleCaptor.getValue());
    }

}

