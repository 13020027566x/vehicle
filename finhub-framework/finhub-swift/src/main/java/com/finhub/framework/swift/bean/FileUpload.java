package com.finhub.framework.swift.bean;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import jakarta.servlet.http.Part;

public class FileUpload {

    private final Part part;

    public FileUpload(Part part) {
        this.part = part;
    }

    public String getFilename() {
        return this.part.getSubmittedFileName();
    }

    public byte[] get() throws IOException {
        return IOUtils.toByteArray(this.part.getInputStream());
    }

    /**
     * Gets the content of this part as an <tt>InputStream</tt>
     *
     * @return The content of this part as an <tt>InputStream</tt>
     * @throws IOException If an error occurs in retrieving the content
     * as an <tt>InputStream</tt>
     */
    public InputStream getInputStream() throws IOException {
        return this.part.getInputStream();
    }

    /**
     * Gets the content type of this part.
     *
     * @return The content type of this part.
     */
    public String getContentType() {
        return this.part.getContentType();
    }

    /**
     * Gets the name of this part
     *
     * @return The name of this part as a <tt>String</tt>
     */
    public String getName() {
        return this.part.getName();
    }

    /**
     * Gets the file name specified by the client
     *
     * @return the submitted file name
     *
     * @since Servlet 3.1
     */
    public String getSubmittedFileName() {
        return this.part.getSubmittedFileName();
    }

    /**
     * Returns the size of this fille.
     *
     * @return a <code>long</code> specifying the size of this part, in bytes.
     */
    public long getSize() {
        return this.part.getSize();
    }

    /**
     * A convenience method to write this uploaded item to disk.
     *
     * <p>This method is not guaranteed to succeed if called more than once for
     * the same part. This allows a particular implementation to use, for
     * example, file renaming, where possible, rather than copying all of the
     * underlying data, thus gaining a significant performance benefit.
     *
     * @param fileName The location into which the uploaded part should
    be stored. The value may be a file name or a path.  The actual
    location of the file in the filesystem is relative to {@link
    javax.servlet.MultipartConfigElement#getLocation()}.  Absolute
    paths are used as provided and are relative to
    <code>getLocation()</code>.  Note: that this is a system
    dependent string and URI notation may not be acceptable on all
    systems. For portability, this string should be generated with
    the File or Path APIs.
     *
     * @throws IOException if an error occurs.
     */
    public void write(String fileName) throws IOException {
        this.part.write(fileName);
    }

    /**
     * Deletes the underlying storage for a file item, including deleting any
     * associated temporary disk file.
     *
     * @throws IOException if an error occurs.
     */
    public void delete() throws IOException {
        this.part.delete();
    }

    /**
     *
     * Returns the value of the specified mime header
     * as a <code>String</code>. If the Part did not include a header
     * of the specified name, this method returns <code>null</code>.
     * If there are multiple headers with the same name, this method
     * returns the first header in the part.
     * The header name is case insensitive. You can use
     * this method with any request header.
     *
     * @param name        a <code>String</code> specifying the
     *                header name
     *
     * @return            a <code>String</code> containing the
     *                value of the requested
     *                header, or <code>null</code>
     *                if the part does not
     *                have a header of that name
     */
    public String getHeader(String name) {
        return this.part.getHeader(name);
    }

    /**
     * Gets the values of the Part header with the given name.
     *
     * <p>Any changes to the returned <code>Collection</code> must not
     * affect this <code>Part</code>.
     *
     * <p>Part header names are case insensitive.
     *
     * @param name the header name whose values to return
     *
     * @return a (possibly empty) <code>Collection</code> of the values of
     * the header with the given name
     */
    public Collection<String> getHeaders(String name) {
        return this.part.getHeaders(name);
    }

    /**
     * Gets the header names of this Part.
     *
     * <p>Some servlet containers do not allow
     * servlets to access headers using this method, in
     * which case this method returns <code>null</code>
     *
     * <p>Any changes to the returned <code>Collection</code> must not
     * affect this <code>Part</code>.
     *
     * @return a (possibly empty) <code>Collection</code> of the header
     * names of this Part
     */
    public Collection<String> getHeaderNames() {
        return this.part.getHeaderNames();
    }

}
