package xp.wheel.easydao.annotation;

import java.lang.annotation.*;
import java.sql.*;

/**
 * @author xp
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {

    String value() default "";

    JDBCType type() default JDBCType.JAVA_OBJECT;

    boolean insertable() default true;

    boolean updatable() default true;

    boolean nullable() default true;

}
