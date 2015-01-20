package org.sausage.picar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by weenie on 07.12.14.
 */
@Controller
@RequestMapping("/car")
public class CarStateController {

    private static final Logger LOG = LoggerFactory.getLogger(CarStateController.class);
    private final CarGpioAdapter carGpioAdapter;


    @Autowired
    public CarStateController(CarGpioAdapter carGpioAdapter) {
        this.carGpioAdapter = carGpioAdapter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    CarState current() {
        return new CarState(carGpioAdapter.getTurn(), carGpioAdapter.getThrottle());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    CarState update(@RequestBody CarState newState) {
        carGpioAdapter.setTurn(newState.getTurn());
        carGpioAdapter.setThrottle(newState.getThrottle());

        return newState;
    }

}
