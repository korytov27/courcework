package utils;

import java.security.SecureRandom;

public class CustomRandom<T> {

    private static String string = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public  String randomString(){
        StringBuilder sb = new StringBuilder(3);

        for (int i = 0; i < 3; i++) {
            sb.append(string.charAt(rnd.nextInt(string.length())));
        }
        return sb.toString();
    }

    public Integer randomInt(){
        return rnd.nextInt(100);
    }

    public Long randomLong(){
        return rnd.nextLong();
    }

    public Double randomDouble(){
        return rnd.nextDouble();
    }

    public Float randomFloat(){
        return rnd.nextFloat();
    }

}
