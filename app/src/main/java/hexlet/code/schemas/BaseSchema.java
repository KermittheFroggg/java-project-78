package hexlet.code.schemas;

import java.util.Map;

public class BaseSchema {

    public boolean isValid(Object obj) {
        return true;
    }

    public boolean isInstance(Object obj, String inst) {
        switch (inst) {
            case "String":
                if (obj instanceof String || obj == null) {
                    return true;
                }
                break;
            case "Number":
                if (obj instanceof Number || obj == null) {
                    return true;
                }
                break;
            case "Map":
                if (obj instanceof Map || obj == null) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
