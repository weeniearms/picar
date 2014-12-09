package org.sausage.picar;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by weenie on 07.12.14.
 */
public class CarController implements MoveEventListener {

    private final MoveEventSource moveEventSource;
    private final CarGpioAdapter carGpioAdapter;


    public CarController(MoveEventSource moveEventSource, CarGpioAdapter carGpioAdapter) {
        this.moveEventSource = moveEventSource;
        this.carGpioAdapter = carGpioAdapter;
    }

    @PostConstruct
    public void init() {
        moveEventSource.addMoveEventListener(this);
    }

    @PreDestroy
    public void tearDown() {
        moveEventSource.removeMoveEventListener(this);
    }

    @Override
    public void moveEventOccurred(MoveEvent moveEvent) {
        switch (moveEvent.getMove()) {

            case FORWARD:
                carGpioAdapter.forward();
                break;
            case BACK:
                carGpioAdapter.back();
                break;
            case STOP:
                carGpioAdapter.stop();
                break;
            case LEFT:
                carGpioAdapter.left();
                break;
            case RIGHT:
                carGpioAdapter.right();
                break;
            case STRAIGHT:
                carGpioAdapter.straight();
                break;
        }
    }
}
