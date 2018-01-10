package hakan.karyagdi.candyorm.library.annotations;

/**
 * Created by hakan on 11/26/17.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DatabaseField {

    public String FieldName() default "";
    public String DataType() default "";
    //public Object DefaultValue() default null;
    public boolean PrimaryKey() default false;
    public boolean AutoIncrement() default false;
    public boolean NotNull() default false;

}