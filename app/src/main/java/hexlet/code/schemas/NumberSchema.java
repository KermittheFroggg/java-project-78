package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {
    boolean isValid = true;
    boolean required = false;
    boolean positive = false;

    List<Integer> range = new ArrayList<>();

    public NumberSchema() {
    }

    public void required() {
        this.required = true;
    }

    public boolean isRequired() {
        return required;
    }

    public void positive() {
        this.positive = true;
    }

    public boolean isPositive() {
        return positive;
    }

    public void range(int begin, int end) {
        for (int i = begin; i <= end; i++) {
            this.range.add(i);
        }
    }

    public List<Integer> getRange() {
        return range;
    }

    public boolean isValid(Object number) {
        this.isValid = true;
        if (number != null) {
            if (!(number instanceof Integer)) {
                return false;
            }
            if (isPositive() && ((Integer) number <= 0)) {
                setIsValidFalse();
            }
            if (!getRange().isEmpty() && !range.contains(number)) {
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
