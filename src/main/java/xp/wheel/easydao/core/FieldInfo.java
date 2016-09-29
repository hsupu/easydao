package xp.wheel.easydao.core;

import java.lang.reflect.*;
import java.sql.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xp
 */
@Data
public class FieldInfo {

    private String propertyName;
    private String fieldName;

    private Class<?> javaType;
    private JDBCType jdbcType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ref {

        private Field field;
        private Method getter;
        private Method setter;

    }

}
