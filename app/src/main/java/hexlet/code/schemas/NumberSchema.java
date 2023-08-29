package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {
    boolean isValid = true;
    boolean required = false;
    boolean positive = false;
    boolean rangeB = false;

    public List<Integer> range = new ArrayList<>();

    public NumberSchema() {
    }

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired(Object obj) {
        if (required && obj == null) {
            return false;
        }
        return true;
    }

    public boolean isRequired() {
        if (required) {
            return false;
        }
        return true;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public boolean isPositive(Object obj) {
        if (positive) {
            return (Integer) obj > 0;
        }
        return true;
    }

    public NumberSchema range(int begin, int end) {
        for (int i = begin; i <= end; i++) {
            this.range.add(i);
            this.rangeB = true;
        }
        return this;
    }

    public boolean isInRange(Object obj) {
        if (rangeB) {
            return range.contains(obj);
        }
        return true;
    }

    public boolean isNotNumber(Object obj) {
        if (obj instanceof Integer) {
            return false;
        }
        return true;
    }

    public boolean isValid(Object number) {
        this.isValid = true;
        if (number != null) {
            if (isNotNumber(number)) {
                return false;
            }
            this.isValid = isPositive(number) && isInRange(number);
        } else {
            this.isValid = isRequired(null);
        }
        return this.isValid;
    }

    public boolean isValid() {
        this.isValid = true;
        this.isValid = isRequired();
        return this.isValid;
    }
}
