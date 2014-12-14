package org.sausage.picar;

import com.pi4j.io.gpio.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Bean(destroyMethod = "shutdown")
    public GpioController gpioController() {
        return GpioFactory.getInstance();
    }

    @Bean
    public GpioPinDigitalOutput movePin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW);
    }

    @Bean
    public GpioPinDigitalOutput moveDirectionPin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, PinState.LOW);
    }

    @Bean
    public GpioPinDigitalOutput turnPin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_12, PinState.LOW);
    }

    @Bean
    public GpioPinDigitalOutput turnDirectionPin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW);
    }
}
