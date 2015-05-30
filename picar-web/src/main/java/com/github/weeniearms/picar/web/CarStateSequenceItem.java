package com.github.weeniearms.picar.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.weeniearms.picar.CarState;

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
