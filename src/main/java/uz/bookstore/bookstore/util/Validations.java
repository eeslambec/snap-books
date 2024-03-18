package uz.bookstore.bookstore.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validations {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    /**
     * if this method send true then it is phoneNumber if not it is email
     * */
    public static boolean isEmailOrPhoneNumber(String str) {
        return str.startsWith("+998");
    }

    public static <T> T requireNonNullElse(T obj,T defaultValue){
        if (obj == null){
            return defaultValue;
        }
        String className = obj.getClass().getSimpleName();
        if (className.equals("String")) {
            if (isNullOrEmpty(obj.toString())){
                return defaultValue;
            }
        } else if (className.equals("Integer") || className.equals("Double")){
            Double integer = (Double) obj;
            if (integer < 0){
                return defaultValue;
            }
        }
        return obj;
    }
}