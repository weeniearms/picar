package org.sausage.picar;

import java.util.EventListener;

/**
 * Created by weenie on 09.12.14.
 */
public interface MoveEventListener extends EventListener {

    void moveEventOccurred(MoveEvent moveEvent);

}
