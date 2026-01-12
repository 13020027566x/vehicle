package com.finhub.framework.core.version;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 可比较 Version
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
public class ComparableVersion implements Comparable<ComparableVersion> {
    private String completeVersion;
    private Integer major;
    private Integer minor;
    private Integer subminor;

    public ComparableVersion(String completeVersion, int major, int minor, int subminor) {
        this.completeVersion = completeVersion;
        this.major = major;
        this.minor = minor;
        this.subminor = subminor;
    }

    public ComparableVersion(int major, int minor, int subminor) {
        this(null, major, minor, subminor);
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getSubminor() {
        return this.subminor;
    }

    /**
     * A string representation of this version. If this version was parsed from, or provided with, a "complete" string which may contain more than just the
     * version number, this string is returned verbatim. Otherwise, a string representation of the version numbers is given.
     *
     * @return string version representation
     */
    @Override
    public String toString() {
        if (this.completeVersion != null) {
            return this.completeVersion;
        }
        return String.format("%d.%d.%d", this.major, this.minor, this.subminor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !ComparableVersion.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        ComparableVersion another = (ComparableVersion) obj;
        if (this.getMajor() != another.getMajor() || this.getMinor() != another.getMinor()
            || this.getSubminor() != another.getSubminor()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash += 19 * hash + this.major;
        hash += 19 * hash + this.minor;
        hash += 19 * hash + this.subminor;
        return hash;
    }

    public int compareTo(ComparableVersion other) {
        int c;
        if ((c = this.major.compareTo(other.getMajor())) != 0) {
            return c;
        } else if ((c = this.minor.compareTo(other.getMinor())) != 0) {
            return c;
        }
        return this.subminor.compareTo(other.getSubminor());
    }

    /**
     * Does this version meet the minimum specified by `min'?
     *
     * @param min The minimum version to compare against.
     * @return true if version meets the minimum specified by `min'
     */
    public boolean meetsMinimum(ComparableVersion min) {
        return compareTo(min) >= 0;
    }

    /**
     * Parse the server version into major/minor/subminor.
     *
     * @param versionString string version representation
     * @return {@link ComparableVersion}
     */
    public static ComparableVersion parseVersion(final String versionString) {
        int point = versionString.indexOf('.');

        if (point != -1) {
            try {
                int serverMajorVersion = Integer.parseInt(versionString.substring(0, point));

                String remaining = versionString.substring(point + 1, versionString.length());
                point = remaining.indexOf('.');

                if (point != -1) {
                    int serverMinorVersion = Integer.parseInt(remaining.substring(0, point));

                    remaining = remaining.substring(point + 1, remaining.length());

                    int pos = 0;

                    while (pos < remaining.length()) {
                        if ((remaining.charAt(pos) < '0') || (remaining.charAt(pos) > '9')) {
                            break;
                        }

                        pos++;
                    }

                    int serverSubminorVersion = Integer.parseInt(remaining.substring(0, pos));

                    return new ComparableVersion(versionString, serverMajorVersion, serverMinorVersion,
                        serverSubminorVersion);
                }
            } catch (NumberFormatException NFE1) {
            }
        }

        // can't parse the server version
        return new ComparableVersion(0, 0, 0);
    }
}
