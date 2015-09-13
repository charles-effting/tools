package org.effting.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory that produces a new {@link Operations} based on the operating system provided by
 * {@link OperatingSystem}.
 *
 * @author Charles Kafels Effting
 */
class OperationsFactory {

    // Log.
    private static final Logger logger = LoggerFactory.getLogger(OperationsFactory.class);

    // Suppresses default constructor, ensuring non-instantiability.
    private OperationsFactory() {
    }

    /**
     * Produces a new {@link Operations} based on the current operating system provided by
     * {@link OperatingSystem#current()}.
     * @return a new instance of {@link Operations}.
     */
    public static Operations create() {
        return create(OperatingSystem.current());
    }

    /**
     * Produces a new {@link Operations} based on the {@link OperatingSystem} provided.
     * @param os the {@link OperatingSystem}.
     * @return a new instance of {@link Operations}.
     */
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
