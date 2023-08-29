package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {
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

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public boolean isPositive(Object obj) {
        if (positive) {
            if (obj == null) {
                return true;
            }
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
            if (obj == null) {
                return true;
            }
            return range.contains(obj);
        }
        return true;
    }

    public boolean isNotNumber(Object obj) {
        if (obj instanceof Number) {
            return false;
        }
        return true;
    }

    public boolean isValid(Object number) {
        if (isNotNumber(number) && number != null) {
            return false;
        }
        return isRequired(number) && isPositive(number) && isInRange(number);
    }
}
