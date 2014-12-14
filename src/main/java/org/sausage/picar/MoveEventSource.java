package org.sausage.picar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by weenie on 09.12.14.
 */
public abstract class MoveEventSource {

    private static final Logger LOG = LoggerFactory.getLogger(MoveEventSource.class);
    private final Collection<MoveEventListener> listeners = new ArrayList<>();


    public void addMoveEventListener(MoveEventListener listener) {
        listeners.add(listener);
    }

    public void removeMoveEventListener(MoveEventListener listener) {
        listeners.remove(listener);
    }

    protected MoveEvent.Move performMove(String move) {
        LOG.info("Got input '{}'", move);

        MoveEvent.Move decodedMove;

        try {
            decodedMove = MoveEvent.Move.valueOf(move.toUpperCase());
        } catch (Exception e) {
            decodedMove = null;
        }

        if (decodedMove != null) {
            LOG.info("Successfully decoded move '{}' from input '{}'", decodedMove, move);
            performMove(decodedMove);
        } else {
            LOG.warn("Failed to decode move from input: {}", move);
        }

        return decodedMove;
    }

    protected void performMove(MoveEvent.Move move) {
        LOG.info("Forwarding move '{}' to listeners", move);
        for (MoveEventListener listener : listeners) {
            listener.moveEventOccurred(new MoveEvent(this, move));
        }
    }

}
