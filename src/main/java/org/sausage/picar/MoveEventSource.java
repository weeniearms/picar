package org.sausage.picar;

/**
 * Created by weenie on 09.12.14.
 */
public interface MoveEventSource {

    void addMoveEventListener(MoveEventListener listener);

    void removeMoveEventListener(MoveEventListener listener);

}
