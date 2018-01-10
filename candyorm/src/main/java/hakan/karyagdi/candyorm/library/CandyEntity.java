package hakan.karyagdi.candyorm.library;
/**
 * Created by hakan on 12/10/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.FontsContract;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import hakan.karyagdi.candyorm.library.annotations.DatabaseField;
import hakan.karyagdi.candyorm.library.db.CandyDb;
import hakan.karyagdi.candyorm.library.util.QueryBuilder;




public class CandyEntity< T  extends  CandyEntity>  {




    public long save(Context context)  {
        return save(CandyDb.getInstance().getWritableDatabase(),this);
    }

    private long save (SQLiteDatabase db, CandyEntity object)
    {
        long result;
        Field[] fields = object.getClass().getDeclaredFields();
        ContentValues contentValues = new ContentValues();


        for (Field field:fields) {
            Annotation[] annotations=field.getDeclaredAnnotations();
            for (Annotation annotation:annotations) {
                String name = ((DatabaseField) annotation).FieldName();
                Log.v("HATA:::::","000");
                String value =String.valueOf(runGetter(field,object));
                contentValues.put(name,value);
            }
        }

        result = db.insertWithOnConflict(object.getClass().getSimpleName(),null,contentValues,SQLiteDatabase.CONFLICT_IGNORE);
        if (result==-1)
        {
            String keyFieldName = getKeyFieldName(object);
            Field keyField = null;
            try {
                keyField = object.getClass().getDeclaredField("opinionId");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            String whereClause=keyFieldName + "=?";
            Log.v("HATA:::::","001");
            String[] whereArgs =new String[]{String.valueOf(runGetter(keyField,object))};

            result = db.update(object.getClass().getSimpleName(),contentValues,whereClause,whereArgs);
        }
        return result;
    }



    public static <T extends CandyEntity> List<T> getAllEntities(Class<T> cls,Context context) {

        return (List<T>) getEntities(CandyDb.getInstance().getWritableDatabase(),cls);
    }

    private static <T extends CandyEntity> List<CandyEntity> getEntities(SQLiteDatabase db,Class<T> cls) {

        ArrayList<CandyEntity> entities = new ArrayList<CandyEntity>();
        Cursor cursor=db.query(cls.getSimpleName(),getColumns(cls),null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {

                CandyEntity entity=null;
                try {
                    entity=cls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Field[] fields = cls.getDeclaredFields();

                for (Field field:fields) {
                    Annotation[] annotations=field.getDeclaredAnnotations();
                    for (Annotation annotation:annotations) {
                            int i = cursor.getColumnIndex(((DatabaseField) annotation).FieldName());
                            runSetter(field,entity,cursor.getString(i));
                    }
                }
                entities.add(entity);
            } while (cursor.moveToNext());
        }

        return entities;
    }

//
//    public static List<IModel> selectRawQuery(String query) {
//        return null;
//    }


    public static void executeRawQuery(String query) {

    }

    private static Object runGetter(Field field, CandyEntity o) {
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {

                    try {
                        return method.invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return null;
    }

    private static Object runSetter(Field field, CandyEntity o,String value) {
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    try {
                        Class<?> cls = method.getParameterTypes()[0];
                        return method.invoke(o,convertStringTo(cls,value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return null;
    }

    private static String getKeyFieldName(CandyEntity o)
    {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field:fields) {
            Annotation[] annotations=field.getDeclaredAnnotations();
            for (Annotation annotation:annotations) {
                if(((DatabaseField) annotation).PrimaryKey())
                {
                    return ((DatabaseField) annotation).FieldName();
                }
            }
        }
        return null;
    }

    private static String[] getColumns(Class<?> cls)
    {
        ArrayList<String> columns =new ArrayList<String>();
        Field[] fields = cls.getDeclaredFields();

        for (Field field:fields) {
            Annotation[] annotations=field.getDeclaredAnnotations();
            for (Annotation annotation:annotations) {
                columns.add(((DatabaseField) annotation).FieldName());

            }
        }
        String[] cols = columns.toArray(new String[0]);
        return cols;
    }

    private static Object convertStringTo(Class<?> cls,String value )
    {
        if(cls==int.class)
        {
            return Integer.valueOf(value);
        }
        else if(cls==Boolean.class)
        {
            return Boolean.valueOf(value);
        }
        else
        {
            Log.v("HATAAAA::",cls.getName());
            return String.valueOf(value);
        }
    }
}
