package org.sausage.picar;

import lombok.Data;

import java.util.EventObject;

/**
 * Created by weenie on 09.12.14.
 */
@Data
public class MoveEvent extends EventObject {

    private final Move move;


    public MoveEvent(Object source, Move move) {
        super(source);

        this.move = move;
    }

    public static enum Move {
        FORWARD, BACK, STOP, LEFT, RIGHT, STRAIGHT
    }
}
