package com.finhub.framework.logback.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.MDCValueLevelPair;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;

/**
 * MDC Dynamic Filter
 * 动态设置当前线程 Logger Level
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/5/12 8:01 下午
 */
public class DynamicThresholdFilter extends TurboFilter {

    private Map<String, Level> valueLevelMap = new HashMap<>();
    private Level defaultThreshold = Level.ERROR;
    private String key;

    private FilterReply onHigherOrEqual = FilterReply.NEUTRAL;
    private FilterReply onLower = FilterReply.DENY;

    /**
     * Get the MDC key whose value will be used as a level threshold
     *
     * @return the name of the MDC key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Get the default threshold value when the MDC key is not set.
     *
     * @return the default threshold value in the absence of a set MDC key
     */
    public Level getDefaultThreshold() {
        return defaultThreshold;
    }

    public void setDefaultThreshold(Level defaultThreshold) {
        this.defaultThreshold = defaultThreshold;
    }

    /**
     * Get the FilterReply when the effective level is higher or equal to the
     * level of current logging request
     *
     * @return FilterReply
     */
    public FilterReply getOnHigherOrEqual() {
        return onHigherOrEqual;
    }

    public void setOnHigherOrEqual(FilterReply onHigherOrEqual) {
        this.onHigherOrEqual = onHigherOrEqual;
    }

    /**
     * Get the FilterReply when the effective level is lower than the level of
     * current logging request
     *
     * @return FilterReply
     */
    public FilterReply getOnLower() {
        return onLower;
    }

    public void setOnLower(FilterReply onLower) {
        this.onLower = onLower;
    }

    /**
     * Add a new MDCValuePair
     */
    public void addMDCValueLevelPair(MDCValueLevelPair mdcValueLevelPair) {
        if (valueLevelMap.containsKey(mdcValueLevelPair.getValue())) {
            addError(mdcValueLevelPair.getValue() + " has been already set");
        } else {
            valueLevelMap.put(mdcValueLevelPair.getValue(), mdcValueLevelPair.getLevel());
        }
    }

    /**
     *
     */
    @Override
    public void start() {
        if (this.key == null) {
            addError("No key name was specified");
        }
        super.start();
        addInfo("DynamicThresholdFilter is started.");
    }

    /**
     * This method first finds the MDC value for 'key'. It then finds the level
     * threshold associated with this MDC value from the list of MDCValueLevelPair
     * passed to this filter. This value is stored in a variable called
     * 'levelAssociatedWithMDCValue'. If it null, then it is set to the
     *
     * @param marker
     * @param logger
     * @param level
     * @param s
     * @param objects
     * @param throwable
     * @return FilterReply - this filter's decision
     * @{link #defaultThreshold} value.
     * <p>
     * If no such value exists, then
     */
    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects,
        Throwable throwable) {

        String mdcValue = MDC.get(this.key);
        if (!isStarted() || StrUtil.isBlank(mdcValue)) {
            return FilterReply.NEUTRAL;
        }

        Level levelAssociatedWithMDCValue = valueLevelMap.get(mdcValue);

        if (levelAssociatedWithMDCValue == null) {
            levelAssociatedWithMDCValue = defaultThreshold;
        }

        if (level.isGreaterOrEqual(levelAssociatedWithMDCValue)) {
            return onHigherOrEqual;
        } else {
            return onLower;
        }
    }

    @Override
    public void stop() {
        super.stop();
        addInfo("DynamicThresholdFilter is stopped.");
    }
}
