package com.github.weeniearms.picar.web;

import org.junit.Test;
import com.github.weeniearms.picar.CarState;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by weenie on 20.01.15.
 */
public class CarStateControllerTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);
    private final CarState state = mock(CarState.class);
    private final String carUrl = "dummyUrl";


    @Test
    public void shouldQueryForCurrentState() {
        // Given
        when(restTemplate.getForObject(carUrl, CarState.class)).thenReturn(state);

        // When
        CarState currentState = new CarStateController(restTemplate, carUrl).current();

        // Then
        assertEquals(state, currentState);
        verify(restTemplate).getForObject(carUrl, CarState.class);
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void shouldUpdateState() {
        // Given
        when(restTemplate.postForObject(carUrl, state, CarState.class)).thenReturn(state);

        // When
        CarState newState = new CarStateController(restTemplate, carUrl).update(state);

        // Then
        assertEquals(state, newState);
        verify(restTemplate).postForObject(carUrl, state, CarState.class);
        verifyNoMoreInteractions(restTemplate);
    }

}
