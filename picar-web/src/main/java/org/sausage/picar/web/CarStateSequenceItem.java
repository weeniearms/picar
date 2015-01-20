package org.sausage.picar.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sausage.picar.CarState;

/**
 * Created by weenie on 20.01.15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStateSequenceItem {

    private CarState state;
    private int duration;

}
