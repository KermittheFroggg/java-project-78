package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    private int minLength;
    private String subString = "";
    private boolean required = false;

    public StringSchema() {
    }

    public final StringSchema required() {
        this.required = true;
        return this;
    }

    public final StringSchema minLength(int minLen) {
        this.minLength = minLen;
        return this;
    }

    public final StringSchema contains(String suBstr) {
        this.subString = suBstr;
        return this;
    }

    public final boolean isMinLength(Object obj) {
        if (obj == null) {
            return true;
        }
        if (minLength != 0) {
            return String.valueOf(obj).length() > minLength;
        }
        return true;
    }

    public final boolean containsSubString(Object obj) {
        if (obj == null) {
            return true;
        }
        if (!subString.equals("")) {
            return String.valueOf(obj).contains(subString);
        }
        return true;
    }

    public final boolean isRequired(Object obj) {
        if (required && (obj == null || String.valueOf(obj).equals(""))) {
            return false;
        }
        return true;
    }

    public final boolean isValid(Object string) {
        if (!isInstance(string, "String")) {
            return false;
        }
        return isRequired(string) && isMinLength(string) && containsSubString(string);
    }
}
