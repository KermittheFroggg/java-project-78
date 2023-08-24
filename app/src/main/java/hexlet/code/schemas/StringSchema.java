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

    public int getMinLength() {
        return minLength;
    }

    public String getSubString() {
        return subString;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isValid(Object string) {
        this.isValid = true;
        if (string != null) {
            if (!(string instanceof String)) {
                setIsValidFalse();
            }
            if (getMinLength() > String.valueOf(string).length()) {
                setIsValidFalse();
            }
            if (!(string.toString().contains(getSubString()))) {
                setIsValidFalse();
            }
            if (isRequired() && string.toString().equals("")) {
                setIsValidFalse();
            }
        } else {
            if (isRequired()) {
                setIsValidFalse();
            }
        }
        return isValid;
    }

    private void setIsValidFalse() {
        this.isValid = false;
    }
}
