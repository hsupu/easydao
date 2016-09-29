package xp.wheel.easydao.util;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.PreparedStatementCreator;

import xp.wheel.easydao.core.FieldInfo;
import xp.wheel.easydao.core.TableInfo;

/**
 * @author xp
 */
public class InsertStatementCreator<T> implements PreparedStatementCreator {

    private TableInfo table;
    private T entity;

    @SuppressWarnings("unchecked")
    public InsertStatementCreator(T entity) {
        this.table = TableManager.initTable(entity.getClass());
        this.entity = entity;
    }

    private String renderFields(Collection<String> fields) {
        return String.join(",", fields.stream().map((e) -> String.format("`%s`", e)).collect(Collectors.toList()));
    }

    private String renderPlaceholders(int count) {
        return StringUtils.repeat("?", ",", count);
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<FieldInfo, FieldInfo.Ref> entry : table.getFields().entrySet()) {
            FieldInfo info = entry.getKey();
            FieldInfo.Ref ref = entry.getValue();
            if (ref.getGetter() != null) {
                try {
                    Object value = ref.getGetter().invoke(entity);
                    if (value == null) continue;
                    map.put(info.getFieldName(), value);
                } catch (InvocationTargetException | IllegalAccessException ignored) {}
            }
        }
        List<String> keys = new ArrayList<>(map.keySet());
        String sql = String.format("INSERT `%s`(%s) VALUES (%s);",
                table.getName(),
                renderFields(keys),
                renderPlaceholders(map.size()));
        PreparedStatement statement = connection.prepareStatement(sql);

        int mapSize = keys.size();
        for (int i = 0; i < mapSize; ++i) {
            statement.setObject(i + 1, map.get(keys.get(i)));
        }
        return statement;
    }
}
