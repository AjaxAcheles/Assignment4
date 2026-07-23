package grail.exceptions;

import java.io.IOException;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.IMPOSSIBLE_ANGLE})
@SuppressWarnings("serial")
public class ImpossibleAngle extends IOException {
    public ImpossibleAngle(String message) {
        super(message);
    }
}
