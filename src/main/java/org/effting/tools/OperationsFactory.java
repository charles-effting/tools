package org.effting.tools;

/**
 *
 * @author Charles Kafels Effting
 */
class OperationsFactory {

    // Suppresses default constructor, ensuring non-instantiability.
    private OperationsFactory() {
    }

    public static Operations create(final OperatingSystem os) {
        switch (os) {
            case UNIX: {
                return new UnixOperations();
            }
            case WINDOWS: {
                throw new UnsupportedOperationException("To be implemented");
            }
            case MAC_OS: {
                throw new UnsupportedOperationException("To be implemented");
            }
            case UNKNOWN: {
                return new DefaultOperations();
            }
            default: {
                throw new IllegalArgumentException(String.format(
                        "Enum '%s' is not supported by the factory", os.name()));
            }
        }
    }
}
