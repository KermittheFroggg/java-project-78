package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapSchema extends BaseSchema {
    private boolean required = false;
    private int size = -1;
    private boolean shape = false;
    private Map<String, BaseSchema> shapeMap = new HashMap();

    public MapSchema() {
    }

    public final MapSchema required() {
        this.required = true;
        return this;
    }

    public final boolean isRequired(Object obj) {
        if (required && obj == null) {
            return false;
        }
        return true;
    }

    public final MapSchema sizeof(int size2) {
        this.size = size2;
        return this;
    }

    public final boolean checkSize(Object obj) {
        if (obj == null) {
            return true;
        }
        if (((Map) obj).size() >= size) {
            return true;
        }
        return false;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        this.shapeMap = new HashMap<>(map);
        this.shape = true;
        return this;
    }

    public final boolean checkShape(Object obj) {
        if (shape) {
            Map<String, BaseSchema> map2 = new HashMap<>((Map) obj);
            for (Entry<String, BaseSchema> entry : map2.entrySet()) {
                if (shapeMap.containsKey(entry.getKey())) {
                    if (!shapeMap.get(entry.getKey()).isValid(entry.getValue())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final boolean isValid(Object obj) {
        if (!isInstance(obj, "Map")) {
            return false;
        }
        return isRequired(obj) && checkSize(obj) && checkShape(obj);
    }
}
