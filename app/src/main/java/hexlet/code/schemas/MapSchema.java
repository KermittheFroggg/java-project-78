package hexlet.code.schemas;
import java.util.Map;

public class MapSchema {
    boolean isValid = true;
    boolean required = false;
    private int size = -1;

    public MapSchema() {
    }

    public void required() {
        this.required = true;
    }

    public boolean isRequired(Object obj) {
        if (required && obj == null) {
            return false;
        }
        return true;
    }

    public void sizeof(int size2) {
        this.size = size2;
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

    public boolean isValid(Object map) {
        if (map != null) {
            if (isNotMap(map)) {
                return false;
            }
            if (size != 0) {
                this.isValid = checkSize((Map) map);
            }
        } else {
            this.isValid = isRequired(null);
        }
        return this.isValid;
    }
}
