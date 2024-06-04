package soundwave;

import com.github.javafaker.Faker;
import org.junit.Ignore;
import org.junit.Test;

public class FakerTest {
    @Ignore
    @Test
    public void fakerGeneratorTest() {
        Faker faker = new Faker();
        System.out.println(faker.name().fullName());
    }
}
