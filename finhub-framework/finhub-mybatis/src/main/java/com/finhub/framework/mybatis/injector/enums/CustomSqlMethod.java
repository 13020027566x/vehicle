package com.finhub.framework.mybatis.injector.enums;

/**
 * 自定义mybatis-plus方法
 *
 * @author zhenxing_liang
 * @version 1.0
 * @since 2023/02/16 17:34
 */
public enum CustomSqlMethod {
    /**
     * 根据ID 物理删除一条数据
     */
    DELETE_BY_ID_PHYSICALLY("deleteByIdPhysically", "根据ID 物理删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
    /**
     * 根据columnMap 条件物理删除记录
     */
    DELETE_BY_MAP_PHYSICALLY("deleteByMapPhysically", "根据columnMap 条件物理删除记录", "<script>\nDELETE FROM %s %s\n</script>"),
    /**
     * 根据 entity 条件物理删除记录
     */
    DELETE_PHYSICALLY("deletePhysically", "根据 entity 条件物理删除记录", "<script>\nDELETE FROM %s %s %s\n</script>"),
    /**
     * 根据ID集合，批量物理删除数据
     */
    DELETE_BATCH_BY_IDS_PHYSICALLY("deleteBatchIdsPhysically", "根据ID集合，批量物理删除数据", "<script>\nDELETE FROM %s WHERE %s IN (%s)\n</script>"),
    ;

    private final String method;
    private final String desc;
    private final String sql;

    CustomSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }
}
