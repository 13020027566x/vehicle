package com.finhub.framework.dubbo.skywalking;

/**
 * Dubbo Plugin Config
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2022/09/23 17:41
 */
public class DubboPluginConfig {

    public static class Plugin {

        public static class Dubbo {
            public static boolean COLLECT_CONSUMER_ARGUMENTS;
            public static int CONSUMER_ARGUMENTS_LENGTH_THRESHOLD;
            public static boolean COLLECT_PROVIDER_ARGUMENTS;
            public static int PROVIDER_ARGUMENTS_LENGTH_THRESHOLD;

            static {
                Dubbo.COLLECT_CONSUMER_ARGUMENTS = false;
                Dubbo.CONSUMER_ARGUMENTS_LENGTH_THRESHOLD = 256;
                Dubbo.COLLECT_PROVIDER_ARGUMENTS = false;
                Dubbo.PROVIDER_ARGUMENTS_LENGTH_THRESHOLD = 256;
            }
        }
    }
}
