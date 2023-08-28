package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema;
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

        assertThat(schema.isValid("wha")).isEqualTo(false);
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
    }

    @Test
    public void testValidatorNumberSchema() {

        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.positive().isValid(null)).isEqualTo(true);

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

        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("фыафы")).isEqualTo(false);
        assertThat(schema.isValid(20)).isEqualTo(false);
        assertThat(schema.isValid(9)).isEqualTo(true);

        schema.range(0, 17);
        assertThat(schema.isValid(4)).isEqualTo(true);
        assertThat(schema.isValid(23)).isEqualTo(false);
    }

    @Test
    public void testValidatorNumberSchema2() {

        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid("5")).isEqualTo(false);
        assertThat(schema.isValid(10)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(true);

        assertThat(schema.positive().isValid(null)).isEqualTo(true);
        schema.range(0, 17);
        assertThat(schema.isValid(4)).isEqualTo(true);
        assertThat(schema.isValid(23)).isEqualTo(false);
        assertThat(schema.positive().isValid(null)).isEqualTo(true);
        assertThat(schema.required().isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(0)).isEqualTo(false);
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

    @Test
    public void testValidatorMapSchemaShape() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isEqualTo(true);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isEqualTo(false);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schema.isValid(human4)).isEqualTo(false);
    }
}
