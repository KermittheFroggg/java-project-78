package hexlet.code.schemas;

import java.util.Map;

public abstract class BaseSchema {

    public abstract boolean isValid(Object obj);

    public final boolean isInstance(Object obj, String inst) {
        boolean isInstance = false;
        switch (inst) {
            case "String":
                if (obj instanceof String || obj == null) {
                    isInstance = true;
                }
                break;
            case "Number":
                if (obj instanceof Number || obj == null) {
                    isInstance = true;
                }
                break;
            case "Map":
                if (obj instanceof Map || obj == null) {
                    isInstance = true;
                }
                break;
            default:
                isInstance = false;
        }
        return isInstance;
    }
}
