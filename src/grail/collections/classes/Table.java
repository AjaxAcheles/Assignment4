package grail.collections.classes;

import java.util.ArrayList;
import java.util.List;

import grail.collections.interfaces.TableInterface;
import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.TABLE})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class Table<T> implements TableInterface<T> {
    private final List<String> keys;
    private final List<T> values;

    public Table() {
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<T>();
    }

    @Override
    public void put(String key, T value) {
        if (key == null || value == null) {
            return;
        }

        int keyIndex = this.keys.indexOf(key);
        if (keyIndex >= 0) {
            this.values.set(keyIndex, value);
        } else {
            this.keys.add(key);
            this.values.add(value);
        }
    }

    @Override
    public T get(String key) {
        int keyIndex = this.keys.indexOf(key);
        if (keyIndex < 0) {
            return null;
        }
        return this.values.get(keyIndex);
    }
}
