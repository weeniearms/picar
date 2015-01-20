package org.sausage.picar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by weenie on 07.12.14.
 */
@Controller
@RequestMapping("/car")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);
    private final CarGpioAdapter carGpioAdapter;


    @Autowired
    public CarController(CarGpioAdapter carGpioAdapter) {
        this.carGpioAdapter = carGpioAdapter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Car state() {
        return new Car(carGpioAdapter.getTurn(), carGpioAdapter.getThrottle());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Car update(@RequestBody Car newState) {
        carGpioAdapter.setTurn(newState.getTurn());
        carGpioAdapter.setThrottle(newState.getThrottle());

        return newState;
    }

}
