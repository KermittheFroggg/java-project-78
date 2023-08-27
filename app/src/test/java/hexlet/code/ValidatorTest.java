package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    public void testValidatorStringSchema() {

        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);

        schema.required();

        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid(5)).isEqualTo(false);
        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);

        schema.contains("wh");
        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);

        schema.contains("whatthe");
        assertThat(schema.isValid("what does the fox say")).isEqualTo(false);

        schema.minLength(4);
        assertThat(schema.isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    public void testValidatorNumberSchema() {

        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        schema.positive();
        assertThat(schema.isValid(null)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("5")).isEqualTo(false);
        assertThat(schema.isValid(10)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(false);
        assertThat(schema.isValid(0)).isEqualTo(false);

        schema.range(5, 10);
        assertThat(schema.isValid(5)).isEqualTo(true);
        assertThat(schema.isValid(10)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(false);
        assertThat(schema.isValid(11)).isEqualTo(false);
    }

    @Test
    public void testValidatorMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isEqualTo(true);

        schema.required();

        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(new HashMap())).isEqualTo(true);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isEqualTo(true);

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isEqualTo(false);

        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isEqualTo(true);
    }
}
