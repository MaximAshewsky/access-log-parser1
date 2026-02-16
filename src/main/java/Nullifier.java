import java.lang.reflect.Field;
import java.util.Arrays;

public class Nullifier {
    public static void nullifyFields(Object obj) {
        if (obj == null) {
            return;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().isPrimitive()) {
                continue;
            }
            field.setAccessible(true);

            try {
                field.set(obj, null);  // Присваиваем null
            } catch (IllegalAccessException e) {
                System.err.println("Не удалось обнулить поле " + field.getName() + ": " + e.getMessage());
            }
        }
    }
}
