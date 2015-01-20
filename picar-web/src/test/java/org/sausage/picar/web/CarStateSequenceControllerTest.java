package org.sausage.picar.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sausage.picar.CarState;
import org.sausage.picar.Throttle;
import org.sausage.picar.Turn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by weenie on 20.01.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarStateSequenceControllerTest {

    @Mock
    private CarStateController carStateController;

    @InjectMocks
    private CarStateSequenceController carStateSequenceController;

    @Test
    public void shouldExecuteSequence() throws InterruptedException {
        // Given
        CarStateSequenceItem[] items = {
                new CarStateSequenceItem(new CarState(Turn.LEFT, Throttle.FORWARD), 1),
                new CarStateSequenceItem(new CarState(Turn.RIGHT, Throttle.BACK), 1),
                new CarStateSequenceItem(new CarState(Turn.STRAIGHT, Throttle.FORWARD), 1),
        };

        CountDownLatch latch = new CountDownLatch(items.length);

        when(carStateController.update(any(CarState.class)))
                .thenAnswer(invocation -> {
                    latch.countDown();
                    return invocation.getArguments()[0];
                });

        // When
        carStateSequenceController.update(items);

        // Then
        assertTrue(latch.await(100, TimeUnit.MILLISECONDS));

        ArgumentCaptor<CarState> carArgumentCaptor = ArgumentCaptor.forClass(CarState.class);
        verify(carStateController, times(items.length)).update(carArgumentCaptor.capture());

        for (int i = 0; i < items.length; i++) {
            assertEquals(items[i].getState(), carArgumentCaptor.getAllValues().get(i));
        }
    }

}
