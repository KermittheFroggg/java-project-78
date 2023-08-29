package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    int minLength;
    String subString = "";
    boolean required = false;

    public StringSchema() {
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int minLen) {
        this.minLength = minLen;
        return this;
    }

    public StringSchema contains(String suBstr) {
        this.subString = suBstr;
        return this;
    }

    public boolean isMinLength(Object obj) {
        if (obj == null) {
            return true;
        }
        if (minLength != 0) {
            return String.valueOf(obj).length() > minLength;
        }
        return true;
    }

    public boolean containsSubString(Object obj) {
        if (obj == null) {
            return true;
        }
        if (!subString.equals("")) {
            return String.valueOf(obj).contains(subString);
        }
        return true;
    }

    public boolean isRequired(Object obj) {
        if (required && (obj == null || String.valueOf(obj).equals(""))) {
            return false;
        }
        return true;
    }

    public boolean isString(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        return false;
    }

    public boolean isValid(Object string) {
        if (!isString(string) && string != null) {
            return false;
        }
        return isRequired(string) && isMinLength(string) && containsSubString(string);
    }
}
