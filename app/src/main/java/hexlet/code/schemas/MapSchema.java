package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapSchema {
    boolean isValid = true;
    boolean required = false;
    private int size = -1;
    boolean shape = false;
    Map<String, BaseSchema> shapeMap = new HashMap();

    public MapSchema() {
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired(Object obj) {
        if (required && obj == null) {
            return false;
        }
        return true;
    }

    public MapSchema sizeof(int size2) {
        this.size = size2;
        return this;
    }

    private boolean checkSize(Map map) {
        if (map.size() >= size) {
            return true;
        }
        return false;
    }

    public boolean isNotMap(Object obj) {
        if (obj instanceof Map) {
            return false;
        }
        return true;
    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        this.shapeMap = new HashMap<>(map);
        this.shape = true;
        return this;
    }

    public boolean isValid(Object map) {
        if (map != null) {
            if (isNotMap(map)) {
                return false;
            }
            if (size != 0) {
                this.isValid = checkSize((Map) map);
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
