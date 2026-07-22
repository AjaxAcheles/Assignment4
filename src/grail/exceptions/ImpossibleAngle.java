package grail.exceptions;

import java.io.IOException;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.IMPOSSIBLE_ANGLE})
public class ImpossibleAngle extends IOException {
    private static final long serialVersionUID = 1L;

    public ImpossibleAngle(String message) {
        super(message);
    }
}
