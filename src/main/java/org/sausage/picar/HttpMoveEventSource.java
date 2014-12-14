package org.sausage.picar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by weenie on 14.12.14.
 */
@Controller
@RequestMapping("/move")
public class HttpMoveEventSource extends MoveEventSource {

    @RequestMapping(value = "/{input}")
    @ResponseBody
    public String move(@PathVariable String input) {
        MoveEvent.Move move = performMove(input);

        return move != null ? move.toString() : null;
    }

}
