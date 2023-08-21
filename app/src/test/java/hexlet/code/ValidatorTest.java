package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    public void testValidatorStringSchema() {

        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);

        schema.required();

        assertThat(false).isEqualTo(schema.isValid(null));
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
}
