package com.finhub.framework.shiro.authc;

import org.apache.shiro.lang.codec.Base64;
import org.apache.shiro.lang.codec.CodecSupport;
import org.apache.shiro.lang.codec.Hex;
import org.apache.shiro.lang.util.ByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * shiro密码盐加密所用 此处为了redis缓存实现序列化
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
public class ShiroByteSource implements ByteSource, Serializable {

    private static final long serialVersionUID = 8238903008975460154L;

    private final byte[] bytes;

    private String cachedHex;

    private String cachedBase64;

    public ShiroByteSource(byte[] bytes) {
        this.bytes = bytes;
    }

    public ShiroByteSource(char[] chars) {
        this.bytes = CodecSupport.toBytes(chars);
    }

    public ShiroByteSource(String string) {
        this.bytes = CodecSupport.toBytes(string);
    }

    public ShiroByteSource(ByteSource source) {
        this.bytes = source.getBytes();
    }

    public ShiroByteSource(File file) {
        this.bytes = (new ShiroByteSource.BytesHelper()).getBytes(file);
    }

    public ShiroByteSource(InputStream stream) {
        this.bytes = (new ShiroByteSource.BytesHelper()).getBytes(stream);
    }

    public static boolean isCompatible(Object o) {
        return o instanceof byte[] || o instanceof char[] || o instanceof String
            || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public boolean isEmpty() {
        return this.bytes == null || this.bytes.length == 0;
    }

    @Override
    public String toHex() {
        if (this.cachedHex == null) {
            this.cachedHex = Hex.encodeToString(this.getBytes());
        }

        return this.cachedHex;
    }

    @Override
    public String toBase64() {
        if (this.cachedBase64 == null) {
            this.cachedBase64 = Base64.encodeToString(this.getBytes());
        }

        return this.cachedBase64;
    }

    @Override
    public String toString() {
        return this.toBase64();
    }

    @Override
    public int hashCode() {
        return this.bytes != null && this.bytes.length != 0 ? Arrays.hashCode(this.bytes) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ByteSource) {
            ByteSource bs = (ByteSource) o;
            return Arrays.equals(this.getBytes(), bs.getBytes());
        } else {
            return false;
        }
    }

    /**
     * Returns a new {@code ByteSource} instance representing the specified string's bytes.  The byte
     * array is obtained assuming {@code UTF-8} encoding.
     *
     * @param salt the string to represent as a {@code ByteSource} instance.
     * @return a new {@code ByteSource} instance representing the specified string's bytes.
     */
    public static ByteSource of(String salt) {
        return new ShiroByteSource(salt);
    }

    private static final class BytesHelper extends CodecSupport {
        private BytesHelper() {
        }

        public byte[] getBytes(File file) {
            return this.toBytes(file);
        }

        public byte[] getBytes(InputStream stream) {
            return this.toBytes(stream);
        }
    }
}
