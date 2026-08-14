package Utils;

import com.github.javafaker.Faker;

public class FakerUtil {



    public static String getUserName(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        return name;
    }
}

