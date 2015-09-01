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
                return new WindowsOperations();
            }
            case MAC_OS: {
                return new MacOperations();
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
