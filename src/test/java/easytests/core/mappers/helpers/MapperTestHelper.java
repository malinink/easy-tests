package easytests.core.mappers.helpers;

import org.apache.ibatis.annotations.Mapper;
import java.lang.reflect.Field;


/**
 * @author malinink
 */
public abstract class MapperTestHelper {

    public static Field findMapperFieldInTest(Class mapperTestClass) {
        final Field[] mapperTestClassFields = mapperTestClass.getDeclaredFields();
        Field mapperTestClassField = null;

        for (Field field: mapperTestClassFields) {
            if (field.getType().isAnnotationPresent(Mapper.class)) {
                mapperTestClassField = field;
                break;
            }
        }
        return mapperTestClassField;
    }
}
