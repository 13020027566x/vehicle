package com.finhub.framework.cache.manager;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;

/**
 * 缓存管理类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class GlobalCacheManager extends AbstractTransactionSupportingCacheManager {

    public static GlobalCacheManager me() {
        return SpringUtil.getBean(GlobalCacheManager.class);
    }

    private Collection<? extends Cache> caches;

    public GlobalCacheManager() {
    }

    /**
     * Specify the collection of Cache instances to use for this CacheManager.
     */
    public void setCaches(final Collection<? extends Cache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

}
