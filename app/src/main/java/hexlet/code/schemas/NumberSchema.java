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

    public void required() {
        this.required = true;
    }

    public boolean isRequired(Object obj) {
        if (required && obj == null) {
            return false;
        }
        return true;
    }

    public void positive() {
        this.positive = true;
    }

    public boolean isPositive(Object obj) {
        if (positive && ((Integer) obj > 0)) {
            return true;
        }
        return false;
    }

    public void range(int begin, int end) {
        for (int i = begin; i <= end; i++) {
            this.range.add(i);
        }
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
        } else {
            this.isValid = isRequired(null);
        }
        return this.isValid;
    }
}
