package org.sonatype.nexus.plugins.rundeck;

import java.util.Comparator;

import org.sonatype.nexus.proxy.maven.metadata.operations.VersionComparator;


@SuppressWarnings("deprecation")
public class OptionComparator  implements Comparator<Option> {
    final private VersionComparator versionComparator = new VersionComparator();

	@Override
	public int compare(Option o1, Option o2) {
		if (o1 == null || o1.getValue() == null || o2 == null || o2.getValue() == null) {
			throw new IllegalArgumentException();
		}

		// negate the result as we want to sort from newest to oldest
		return -(versionComparator.compare(o1.getValue(), o2.getValue()));
	}
	
}