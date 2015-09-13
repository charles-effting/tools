package org.effting.tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An enum that identifies the current operating system where the application is running.
 *
 * @author Charles Kafels Effting
 */
public enum OperatingSystem {

    /**
     * Windows family.
     */
    WINDOWS("win"),

    /**
     * Macintosh family.
     */
    MAC_OS("mac"),

    /**
     * UNIX family.
     */
    UNIX("solaris", "sunos", "linux", "unix"),

    /**
     * An unknown operating system.
     */
    UNKNOWN("unknown");

    private final Set<String> familySet = new HashSet<>();

    OperatingSystem(final String os, final String... others) {
        // Adds the operating system names to the set.
        familySet.add(os);

        if (others != null) {
            Collections.addAll(familySet, others);
        }
    }

    /**
     * Detects the current operating system based on the system property {@code os.name}.
     * @return an instance of {@link OperatingSystem}.
     */
    public static OperatingSystem current() {
        final String osName = System.getProperty("os.name");
        if (osName != null) {
            final String osNameLowerCase = osName.toLowerCase();

            for (final OperatingSystem os : values()) {
                for (String family : os.familySet) {
                    if (osNameLowerCase.contains(family)) {
                        return os;
                    }
                }
            }
        }

        return UNKNOWN;
    }
}