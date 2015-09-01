package org.effting.tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public enum OperatingSystem {

    WINDOWS("win"),

    MAC_OS("mac"),

    UNIX("solaris", "sunos", "linux", "unix"),

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
     *
     * @return
     */
    public static OperatingSystem current() {
        final String name = System.getProperty("os.name");
        if (name != null) {
            final String nameLowerCase = name.toLowerCase();

            for (final OperatingSystem os : values()) {
                for (String family : os.familySet) {
                    if (family.contains(nameLowerCase)) {
                        return os;
                    }
                }
            }
        }

        return UNKNOWN;
    }
}