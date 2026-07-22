package grail.collections.interfaces;

import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.TABLE})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public interface TableInterface<T> {
    void put(String key, T value);
    T get(String key);
}
