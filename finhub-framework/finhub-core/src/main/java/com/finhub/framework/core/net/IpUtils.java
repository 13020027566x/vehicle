package com.finhub.framework.core.net;

import cn.hutool.extra.servlet.ServletUtil;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.json.JsonUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.core.web.WebUtils;
import com.finhub.framework.exception.UtilException;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * IP工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@UtilityClass
public class IpUtils {

    public static final String CITY_NAME = "市";
    public static final String UNKNOWN = "unknown";
    public static final String LOCAL_IP = "127.0.0.1";
    public static final int IP_LENGTH = 15;
    public static final String LOCAL_IP_2 = "0:0:0:0:0:0:0:1";
    /**
     * 查找ip所在城市：新浪
     */
    private static String IP_CITY_URL_FIRST = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

    /**
     * 查找ip所在城市：淘宝
     */
    private static String IP_CITY_URL_SECOND = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    public static void main(String[] args) throws Exception {
        String result = IpUtils.getCityNameByIP("58.211.8.108", 30000, 30000);
        if (StrUtil.isNotBlank(result)) {
            System.out.println(result);
        } else {
            System.out.println("无法识别");
        }
    }

    /**
     * 根据ip获取所属城市名称
     *
     * @param ipString
     * @return
     * @throws Exception
     */
    public static String getCityNameByIP(String ipString, int connectTimeOut, int readTimeOut) throws Exception {
        String cityName = StrConstants.S_EMPTY;
        // 新浪
        String temp = IpUtils.doGet(IP_CITY_URL_FIRST + ipString, connectTimeOut, readTimeOut);
        if (StrUtil.isNotBlank(temp)) {
            AreaDTO areaDTO = JsonUtils.toObj(temp, AreaDTO.class);
            if (areaDTO != null) {
                String city = areaDTO.getCity();
                if (StrUtil.isNotBlank(city) && !StrUtil.endWith(city, CITY_NAME, true)) {
                    cityName = city + CITY_NAME;
                }
            }
        }
        if (StrUtil.isBlank(cityName)) {
            // 淘宝
            temp = IpUtils.doGet(IP_CITY_URL_SECOND + ipString, connectTimeOut, readTimeOut);
            if (StrUtil.isNotBlank(temp)) {
                AreaResultDTO areaResultDTO = JsonUtils.toObj(temp, AreaResultDTO.class);
                AreaDTO areaDTO = areaResultDTO.getData();
                if (areaDTO != null) {
                    return areaDTO.getCity();
                }
            }
        }
        return cityName;
    }

    /**
     * 淘宝接口返回结果数据
     */
    public static class AreaResultDTO {

        private AreaDTO data;

        /**
         * get data
         *
         * @return
         */
        public AreaDTO getData() {
            return data;
        }

        /**
         * set data
         *
         * @param data
         */
        public void setData(AreaDTO data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "AreaResultDTO{" +
                "data=" + data +
                '}';
        }
    }


    /**
     * 区域dto
     */
    public static class AreaDTO {

        /**
         * 国家
         */
        private String country;
        /**
         * 省
         */
        private String province;
        /**
         * 市
         */
        private String city;

        /**
         * get country
         *
         * @return
         */
        public String getCountry() {
            return country;
        }

        /**
         * set country
         *
         * @param country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * get province
         *
         * @return
         */
        public String getProvince() {
            return province;
        }

        /**
         * set province
         *
         * @param province
         */
        public void setProvince(String province) {
            this.province = province;
        }

        /**
         * get city
         *
         * @return
         */
        public String getCity() {
            return city;
        }

        /**
         * set city
         *
         * @param city
         */
        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "AreaDTO{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
        }
    }

    /**
     * 获取请求真实Ip地址
     *
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            return getRemoteIp(request);
        }
        return getFirstRealIp(ipAddress);
    }

    /**
     * 获取请求远程调用ip地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        if (StrUtil.equals(LOCAL_IP, ipAddress) || StrUtil.equals(LOCAL_IP_2, ipAddress)) {
            try {
                // 根据网卡取本机配置的IP
                InetAddress inet = InetAddress.getLocalHost();
                ipAddress = inet.getHostAddress();
            } catch (UnknownHostException e) {
                log.warn("get request remote address fail : ", e);
            }
        }
        return getFirstRealIp(ipAddress);
    }

    /**
     * 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
     *
     * @param ipAddress
     * @return
     */
    private static String getFirstRealIp(String ipAddress) {
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
            if (ipAddress.indexOf(StrConstants.S_COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(StrConstants.S_COMMA));
            }
        }
        return ipAddress;
    }

    /**
     * do get
     *
     * @param url
     * @return
     * @throws Exception
     */
    private static String doGet(String url, int connectTimeOut, int readTimeOut) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {

            URL realUrl = new URL(url);
            // open connection
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(connectTimeOut);
            connection.setReadTimeout(readTimeOut);
            // connect
            connection.connect();
            // define BufferedReader to read input content
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharsetUtils.DEFAULT_CHARSET));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new UtilException(e);
        } finally {
            IoUtil.close(in);
        }
        return result.toString();
    }

    /**
     * 获取客户端ip. 请求体默认从全局上下文获取.
     *
     * @param otherHeaderNames
     * @return
     */
    public static String getClientIP(String... otherHeaderNames) {
        HttpServletRequest request = WebUtils.getRequest();
        if (request == null) {
            return null;
        }

        return getClientIP(request, otherHeaderNames);
    }

    /**
     * 获取客户端ip
     *
     * @param request          请求对象
     * @param otherHeaderNames 其他自定义头文件
     * @return
     */
    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        return JakartaServletUtil.getClientIP(request, otherHeaderNames);
    }

    /**
     * <p>ip使用int表示.</p>
     * <p>
     * ip有4段, 每段最大值为255, 即 2^8 - 1, 刚好是一个字节能表示的最大值,
     * 所以4个字节的int刚好能用来表示一个ip地址.
     * </p>
     *
     * @param ip
     * @return
     * @deprecated java 没有无符号整型. 请使用 {@link #ipToLong(String)}
     */
    @Deprecated
    public static Integer ipToInt(String ip) {
        String[] ips = ip.split("\\.");
        int ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for (String ip4 : ips) {
            int ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }

        return ipFour;
    }

    /**
     * 使用int表示的ip地址, 转换成字符串的ip
     *
     * @param ip 使用int表示的ip地址
     * @return
     * @deprecated java 没有无符号整型. 请使用 {@link #longToIp(long)}
     */
    @Deprecated
    public static String intToIp(Integer ip) {
        //思路很简单，每8位拿一次，就是对应位的IP
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int ipa = (ip >> (8 * i)) & (0xff);
            sb.append(".").append(ipa);
        }

        return sb.substring(1);
    }

    /**
     * <p>ip使用long表示.</p>
     * <p>
     * ip有4段, 每段最大值为255, 即 2^8 - 1, 刚好是一个字节能表示的最大值,
     * 所以4个字节的int刚好能用来表示一个ip地址.
     * </p>
     *
     * @param ip
     * @return
     */
    public static long ipToLong(String ip) {
        String[] ips = ip.split("\\.");
        long ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for (String ip4 : ips) {
            int ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }

        return ipFour;
    }

    /**
     * 使用long表示的ip地址, 转换成字符串的ip
     *
     * @param ip 使用int表示的ip地址
     * @return
     */
    public static String longToIp(long ip) {
        //思路很简单，每8位拿一次，就是对应位的IP
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int ipa = (int) ((ip >> (8 * i)) & (0xff));
            sb.append(".").append(ipa);
        }

        return sb.substring(1);
    }
}
