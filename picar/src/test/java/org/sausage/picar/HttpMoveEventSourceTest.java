package org.sausage.picar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by weenie on 14.12.14.
 */
@RunWith(Parameterized.class)
public class HttpMoveEventSourceTest {

    private final String input;
    private final MoveEvent.Move move;
    private final MoveEventListener listener = mock(MoveEventListener.class);


    public HttpMoveEventSourceTest(String input, MoveEvent.Move move) {
        this.input = input;
        this.move = move;
    }

    @Parameterized.Parameters
    public static Collection moves() {
        return Arrays.asList(new Object[][]{
                {"forward", MoveEvent.Move.FORWARD},
                {"FORWARD", MoveEvent.Move.FORWARD},
                {"back", MoveEvent.Move.BACK},
                {"stop", MoveEvent.Move.STOP},
                {"left", MoveEvent.Move.LEFT},
                {"right", MoveEvent.Move.RIGHT},
                {"straight", MoveEvent.Move.STRAIGHT},
                {"invalid", null},
                {"", null},
                {null, null}
        });
    }

    @Test
    public void shouldProperlyDecodeMove() {
        // Given
        HttpMoveEventSource source = new HttpMoveEventSource();
        source.addMoveEventListener(listener);

        // When
        String result = source.move(input);

        // Then
        if (move == null) {
            assertNull(result);
            verifyZeroInteractions(listener);
        } else {
            assertEquals(move.toString(), result);
            ArgumentCaptor<MoveEvent> captor = ArgumentCaptor.forClass(MoveEvent.class);
            verify(listener).moveEventOccurred(captor.capture());
            assertEquals(move, captor.getValue().getMove());
        }

    }

}
