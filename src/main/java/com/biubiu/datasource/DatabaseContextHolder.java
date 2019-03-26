package com.biubiu.datasource;

import com.biubiu.constants.DatabaseType;

/**
 * Created by Haibiao.Zhang on 2019-03-26 09:51
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType databaseType) {
        if (databaseType == null) throw new NullPointerException("databaseType require not null");
        contextHolder.set(databaseType);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void clearDatabaseType() {
        contextHolder.remove();
    }

}
