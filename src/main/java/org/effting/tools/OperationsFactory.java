package org.effting.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Charles Kafels Effting
 */
class OperationsFactory {

    // Log.
    private static final Logger logger = LoggerFactory.getLogger(OperationsFactory.class);

    // Suppresses default constructor, ensuring non-instantiability.
    private OperationsFactory() {
    }

    public static Operations create(final OperatingSystem os) {
        // Log.
        logger.trace("Creating an Operations instance for {}", os.name());

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
