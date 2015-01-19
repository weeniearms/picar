package org.sausage.picar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

/**
 * Created by weenie on 09.12.14.
 */
public class SystemInMoveEventSource extends MoveEventSource implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SystemInMoveEventSource.class);


    @Override
    public void run(String... strings) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    String input = scanner.nextLine();
                    performMove(input);
                } catch (Exception e) {
                    LOG.error("Error occurred while reading input", e);
                }
            }
        }
    }

}
