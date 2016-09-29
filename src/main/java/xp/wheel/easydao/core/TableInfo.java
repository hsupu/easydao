package xp.wheel.easydao.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xp
 */
@Data
public class TableInfo {

    private String name;

    private String catelog;

    private String schema;

    private Map<FieldInfo, FieldInfo.Ref> fields;

}
