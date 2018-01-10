package hakan.karyagdi.candyorm.library.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

import hakan.karyagdi.candyorm.library.CandyEntity;
import hakan.karyagdi.candyorm.library.annotations.DatabaseField;


/**
 * Created by hakan on 12/3/17.
 */

public class QueryBuilder {

    public static String createTableScript(Class<? extends CandyEntity> databaseTable) {
        String query = "CREATE TABLE " + databaseTable.getSimpleName() + "(";
        Field[] fields = databaseTable.getDeclaredFields();
        Collections.reverse(Arrays.asList(fields));
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
/*            Log.v("Loop1","Loop1");*/
            for (Annotation annotation : annotations) {
                DatabaseField databaseField = (DatabaseField) annotation;
                String column = databaseField.FieldName() + " ";
                column += databaseField.DataType() + " ";
                column += isPrimaryKey(databaseField.PrimaryKey()) + " ";
                column += isAutoIncrement(databaseField.AutoIncrement()) + " ";
                column += isNotNull(databaseField.NotNull()) + " ";
                //column+=isPrimaryKey(databaseField.PrimaryKey())+" ";
                column += ",";
                query += column;
//                Log.v("Loop2","Loop2");
            }
        }
        query =fixEndWithComma(query) + ")";
        return query;
    }

    private static String isAutoIncrement(boolean b1) {
        return b1 == true ? "AUTOINCREMENT" : "";
    }

    private static String isNotNull(boolean b1) {
        return b1 == true ? "NOT NULL" : "";
    }

    private static String isPrimaryKey(boolean b1) {
        return b1 == true ? "PRIMARY KEY" : "";
    }
    public static String fixEndWithComma(String s)
    {
       return s.endsWith(",")==true?s.substring(0,s.length()-2):s;
    }
}
