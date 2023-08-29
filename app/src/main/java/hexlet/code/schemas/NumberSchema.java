package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class NumberSchema extends BaseSchema {
    boolean required = false;
    boolean positive = false;
    boolean rangeB = false;

    public List<Integer> range;

    public NumberSchema() {
    }

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequired(Object obj) {
        if (required) {
            return isNumber(obj);
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
        this.range = new ArrayList<>();
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

    public boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        }
        return false;
    }

    public boolean isValid(Object number) {
        if (!isNumber(number) && number != null) {
            return false;
        }
        return isRequired(number) && isPositive(number) && isInRange(number);
    }
}
