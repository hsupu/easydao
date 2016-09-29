package xp.wheel.easydao.util;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import xp.wheel.easydao.annotation.Field;
import xp.wheel.easydao.core.FieldInfo;
import xp.wheel.easydao.core.TableInfo;

/**
 * @author xp
 */
public class TableManager {

    private static final Map<Class<?>, TableInfo> tables = new HashMap<>();

    public static synchronized TableInfo initTable(Class<?> clazz) {
        TableInfo tableInfo = new TableInfo();

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnno = clazz.getAnnotation(Table.class);
            tableInfo.setName(tableAnno.name().length() > 0 ? tableAnno.name() : clazz.getName());
            tableInfo.setCatelog(tableAnno.catalog());
            tableInfo.setSchema(tableAnno.schema());
        } else {
            tableInfo.setName(clazz.getName());
        }

        java.lang.reflect.Field[] clazzFields = clazz.getDeclaredFields();
        Collection<java.lang.reflect.Field> validFields =
                Arrays.stream(clazzFields)
                        .filter(field -> field.isAnnotationPresent(Field.class))
                        .collect(Collectors.toList());

        Map<FieldInfo, FieldInfo.Ref> fieldInfos = new HashMap<>();
        for (java.lang.reflect.Field field : validFields) {
            Field fieldAnno = field.getAnnotation(Field.class);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setPropertyName(field.getName());
            fieldInfo.setFieldName(fieldAnno.value().length() > 0 ? fieldAnno.value() : field.getName());
            fieldInfo.setJavaType(field.getType());
            fieldInfo.setJdbcType(fieldAnno.type());

            FieldInfo.Ref ref = new FieldInfo.Ref();
            ref.setField(field);
            String capitalized = StringUtils.capitalize(field.getName());
            try {
                Method getter = clazz.getMethod("get" + capitalized);
                ref.setGetter(getter);
            } catch (NoSuchMethodException ignored) {}
            try {
                Method setter = clazz.getMethod("set" + capitalized, field.getType());
                ref.setSetter(setter);
            } catch (NoSuchMethodException ignored) {}
            fieldInfos.put(fieldInfo, ref);
        }
        tableInfo.setFields(fieldInfos);

        tables.put(clazz, tableInfo);
        return tableInfo;
    }

}
