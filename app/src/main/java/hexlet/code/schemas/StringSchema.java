package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    boolean isValid = true;
    int minLength;
    String subString = "";
    boolean required = false;

    public StringSchema() {
    }

    public void required() {
        this.required = true;
    }

    public void minLength(int minLen) {
        this.minLength = minLen;
    }

    public void contains(String suBstr) {
        this.subString = suBstr;
    }

    public boolean isMinLength(Object obj) {
        if (String.valueOf(obj).length() > minLength) {
            return true;
        }
        return false;
    }

    public boolean containsSubString(Object obj) {
        if (String.valueOf(obj).contains(subString)) {
            return true;
        }
        return false;
    }

    public boolean isRequired(Object obj) {
        if (required && (obj == null || String.valueOf(obj).equals(""))) {
            return false;
        }
        return true;
    }

    public boolean isNotString(Object obj) {
        if (obj instanceof String) {
            return false;
        }
        return true;
    }

    public boolean isValid(Object string) {
        if (string != null) {
            if (isNotString(string)) {
                return false;
            }
            if (minLength != 0) {
                this.isValid = isMinLength(string);
            }
            if (subString.equals("")) {
                this.isValid = isRequired(string);
            }
            if (!subString.equals("")) {
                this.isValid = containsSubString(string);
            }
        } else {
            isValid = isRequired(null);
        }
        return isValid;
    }
}
