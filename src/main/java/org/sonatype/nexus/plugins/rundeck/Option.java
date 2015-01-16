package org.sonatype.nexus.plugins.rundeck;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * JavaBean representation of a RunDeck option
 */
public class Option implements Serializable {

	private static final long serialVersionUID = -2815986041659802032L;
	
	private final String name;
    private final String value;

    public Option(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public Option(String version, long lastModified) {
    	this(version + " (" + DateFormatUtils.ISO_DATETIME_FORMAT.format(lastModified) + ")", version);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
    	return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return "Option [name=" + name + ", value=" + value + "]";
    }
}
