package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapSchema {
    private boolean isValid = true;
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

    public final boolean checkSize(Map map) {
        if (map.size() >= size) {
            return true;
        }
        return false;
    }

    public final boolean isNotMap(Object obj) {
        if (obj instanceof Map) {
            return false;
        }
        return true;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        this.shapeMap = new HashMap<>(map);
        this.shape = true;
        return this;
    }

    public final boolean isValid(Object map) {
        this.isValid = true;
        if (map != null) {
            if (isNotMap(map)) {
                return false;
            }
            if (size != 0) {
                this.isValid = checkSize((Map) map);
            }
            if (shape) {
                Map<String, BaseSchema> map2 = new HashMap<>((Map) map);
                for (Entry<String, BaseSchema> entry : map2.entrySet()) {
                    if (shapeMap.containsKey(entry.getKey())) {
                        if (!shapeMap.get(entry.getKey()).isValid(entry.getValue())) {
                            return false;
                        }
                    }
                }
            }
        } else {
            this.isValid = isRequired(null);
        }
        return this.isValid;
    }
}
