package xp.wheel.easydao;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import xp.wheel.easydao.util.InsertStatementCreator;

/**
 * @author xp
 */
@Repository
public class EasyDao<T extends Serializable> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer insert(T entity) {
        return jdbcTemplate.update(new InsertStatementCreator<>(entity));
    }

}
