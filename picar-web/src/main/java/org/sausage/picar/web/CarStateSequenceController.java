package org.sausage.picar.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by weenie on 20.01.15.
 */
@Controller
@RequestMapping("/api/sequence")
public class CarStateSequenceController {

    private static final Logger LOG = LoggerFactory.getLogger(CarStateSequenceController.class);
    private final ExecutorService taskExecutor;
    private final CarStateController carStateController;

    @Autowired
    public CarStateSequenceController(CarStateController carStateController) {
        this.taskExecutor = Executors.newSingleThreadExecutor();
        this.carStateController = carStateController;
    }

    @PreDestroy
    public void tearDown() {
        this.taskExecutor.shutdownNow();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody CarStateSequenceItem[] update(@RequestBody CarStateSequenceItem[] newStates) {
        for (CarStateSequenceItem item : newStates) {
            Runnable runnable = () -> {
                try {
                    carStateController.update(item.getState());
                    Thread.sleep(item.getDuration());
                } catch (Exception e) {
                    LOG.error("Error occurred while executing sequence item {}", item, e);
                }
            };

            taskExecutor.submit(runnable);
        }

        return newStates;
    }

}
