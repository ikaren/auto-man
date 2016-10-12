package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/10.
 */
public enum DataType {
    CHAR("CHAR", "String"),
    FLOAT("FLOAT", "Float"),
    TIMESTAMP("TIMESTAMP", "Date"),
    TINYINT("TINYINT", "Integer"),
    VARCHAR("VARCHAR", "String"),
    DOUBLE("DOUBLE", "Double"),
    INTEGER("INTEGER", "Integer"),
    BIGINT("BIGINT", "Long"),
    DECIMAL("DECIMAL", "Double");

    public final String jdbcType;
    public final String javaType;

    DataType(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static DataType getType(String jdbcType) {
        if (null == jdbcType || 0 == jdbcType.length()) {
            return null;
        }

        return DataType.valueOf(jdbcType);
    }
}
