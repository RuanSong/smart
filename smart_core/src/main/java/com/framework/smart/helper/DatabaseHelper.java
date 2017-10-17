package com.framework.smart.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.framework.smart.util.CollectionUtil;
import org.framework.smart.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库操作帮助类
 *
 * @author rosan
 * @date: 2017/10/17 下午10:46
 * @version:1.0
 */
public final class DatabaseHelper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    private static final String DRIVER;
    private static final String URL;
    private static final String USERANME;
    private static final String PASSWORD;

    static {
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();
        Properties properties = PropsUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERANME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");
        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERANME);
        DATA_SOURCE.setPassword(PASSWORD);
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            logger.error("begin transaction failed...", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 事务提交
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        try {
            if (conn != null) {
                conn.commit();
                conn.close();
            }
        } catch (Exception e) {
            logger.error("commit transaction failed...", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_HOLDER.remove();
        }
    }

    /**
     * 事务回滚
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        try {
            if (conn != null) {
                conn.rollback();
                conn.close();
            }
        } catch (Exception e) {
            logger.error("rollback transaction failed...", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_HOLDER.remove();
        }
    }

    /**
     * 查询实体列表
     *
     * @param entryClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entryClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = DATA_SOURCE.getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entryClass), params);
        } catch (SQLException e) {
            logger.error("query entity list failed...", e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

    /**
     * 执行查询语句
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            Connection conn = DATA_SOURCE.getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (Exception e) {
            logger.error("execute query failed...", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询单个实体对象
     *
     * @param entryClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entryClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = DATA_SOURCE.getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entryClass), params);
        } catch (Exception e) {
            logger.error("query entity failed...", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * 执行更新语句(包括 upate,insert,delete)
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection conn = DATA_SOURCE.getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (Exception e) {
            logger.error("execute upate faild...", e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     *
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.error("can't insert entity: fieldMap is empty");
            return false;
        }
        String sql = "INSERT INTO " + getTablename(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf("?, "), values.length(), ")");
        sql += columns + " VALUES " + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;

    }

    /**
     * 更新实体
     *
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.error("can't update entity: fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTablename(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(",")) + " WHERE id=?";
        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "DELETE FROM " + getTablename(entityClass) + " WHERE id=?";
        return executeUpdate(sql, id) == 1;
    }

    /**
     * 获取实体映射对应的表名
     *
     * @param entityClass
     * @return
     */
    private static String getTablename(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }

    /**
     * 执行sql文件
     *
     * @param filePath
     */
    public static void executeSqlFile(String filePath) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while ((sql = reader.readLine()) != null) {
                executeUpdate(sql);
            }
        } catch (Exception e) {
            logger.error("execute sql file failed...", e);
        }
    }

    /**
     * 就获取数据库链接
     *
     * @return
     */
    private static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("get connection failed", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * 关闭数据库链接(使用数据库链接池之后不用手动关闭数据库链接了)
     */
    @Deprecated
    private static void closeConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("close connection failed..", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }


}
