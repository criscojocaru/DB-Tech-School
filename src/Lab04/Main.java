package Lab04;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Main {

    public static String toJson(Object object) throws JsonSerializeException {
        try {
            Class<?> objectClass = requireNonNull(object).getClass();
            Map<String, String> jsonElements = new HashMap<>();
            for (Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonField.class)) {

                    String key = getSerializedKey(field);
                    String value = String.valueOf(field.get(object));
                    int lengthToTrimString = field.getAnnotation(JsonField.class).lengthToTrimString();
                    int trimmedValue = value.length() - lengthToTrimString;

                    if (lengthToTrimString != 255) {
                        jsonElements.put(key, String.valueOf(trimmedValue));
                    } else {
                        jsonElements.put(key, value);
                    }
                }
            }
            return toJsonString(jsonElements);
        }
        catch (IllegalAccessException e) {
            throw new JsonSerializeException(e.getMessage());
        }
    }

    private static String toJsonString(Map<String, String> jsonMap) {
        String elementsString = jsonMap.entrySet()
                .stream()
                .map(entry -> "\""  + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + elementsString + "}";
    }

    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).name();
        if (annotationValue.isEmpty()) {
            return field.getName();
        }
        else {
            return annotationValue;
        }
    }

    public static void main(String[] args) throws JsonSerializeException {
        Product product = new Product(100, "Coca-Cola", "A tasty delicious drink", "Carrefour");

        String json = toJson(product);
        System.out.println(json); // { "code": 100, "name": "Coca-Cola", "description": 3}
    }
}
