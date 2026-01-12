package com.finhub.framework.mybatis.generator;

import com.finhub.framework.mybatis.config.DaoConfig;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrFormatter;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;

/**
 * 自定义业务前缀 ID 生成器，集成自默认生成器
 * （废弃，待处理）
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Deprecated
public class CustomBusinessIdGenerator extends DefaultIdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        String idPattern = DaoConfig.me().getIdPattern();
        return CharSequenceUtil.isBlank(idPattern) ?
            super.nextId(entity) :
            Long.valueOf(StrFormatter.format(idPattern, super.nextId(entity)));
    }

}
