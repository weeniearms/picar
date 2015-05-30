package com.github.weeniearms.picar.web;

import com.github.weeniearms.picar.CarState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by weenie on 20.01.15.
 */
@Controller
@RequestMapping("/api/car")
public class CarStateController {

    private static final Logger LOG = LoggerFactory.getLogger(CarStateController.class);
    private final RestTemplate restTemplate;
    private final String carUrl;


    @Autowired
    public CarStateController(RestTemplate restTemplate, @Value("${picar.url}") String carUrl) {
        this.restTemplate = restTemplate;
        this.carUrl = carUrl;

        LOG.info("Using car url {}", carUrl);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    CarState current() {
        return restTemplate.getForObject(carUrl, CarState.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    CarState update(@RequestBody CarState newState) {
        return restTemplate.postForObject(carUrl, newState, CarState.class);
    }

}
