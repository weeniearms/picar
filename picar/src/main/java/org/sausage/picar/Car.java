package org.sausage.picar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by weenie on 20.01.15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Turn turn;

    private Throttle throttle;

}
