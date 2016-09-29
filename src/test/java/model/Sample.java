package model;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import xp.wheel.easydao.annotation.Field;

/**
 * @author xp
 */
@Table(name = "Sample")
@Data
public class Sample implements Serializable {

    private static final long serialVersionUID = 7287053221195069567L;

    @Id
    @Field(value = "id", type = JDBCType.INTEGER)
    private Integer id;

    @Field(type = JDBCType.VARCHAR)
    private String name;

    @Field(type = JDBCType.DATE, insertable = false, updatable = false)
    private Date createTime;

    @Field(type = JDBCType.TIMESTAMP, insertable = false, updatable = false)
    private Timestamp version;

}
