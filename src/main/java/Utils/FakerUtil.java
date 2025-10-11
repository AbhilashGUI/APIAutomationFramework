package Utils;

import com.github.javafaker.Faker;

public class FakerUtil {

    static Faker faker;
   // Declares a static instance variable of type Faker.
    public static String getUserName(){
        faker = new Faker();
        String name = faker.name().fullName();
        return name;
    }
}

