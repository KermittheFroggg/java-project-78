package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {
    boolean isValid = true;
    boolean required = false;
    boolean positive = false;

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

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public boolean isPositive(Object obj) {
        if (positive && ((Integer) obj > 0)) {
            return true;
        }
        return false;
    }

    public NumberSchema range(int begin, int end) {
        for (int i = begin; i <= end; i++) {
            this.range.add(i);
        }
        return this;
    }

    public boolean isInRange(Object obj) {
        if (range.contains(obj)) {
            return true;
        }
        return false;
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
            if (positive) {
                this.isValid = isPositive(number);
            }
            if (!range.isEmpty()) {
                this.isValid = isInRange(number);
            }
            if (positive && !range.isEmpty()) {
                this.isValid = isInRange(number) && isPositive(number);
            }
        } else {
            this.isValid = isRequired(number);
        }
        return this.isValid;
    }
}
