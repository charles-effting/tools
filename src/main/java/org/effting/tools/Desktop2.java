package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author Charles Kafels Effting
 */
public class Desktop2 {

    private static final Desktop2 INSTANCE = new Desktop2();

    private final Operations operations;

    // Suppresses default constructor, ensuring non-instantiability.
    private Desktop2() {
        // Gets the underlying implementation based on the current operating system.
        operations = OperationsFactory.create(OperatingSystem.current());
    }

    /**
     *
     * @return
     */
    public static Desktop2 getDesktop() {
        return INSTANCE;
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void open(final File file) throws IOException {
        operations.open(file);
    }

    /**
     *
     * @param uri
     * @throws IOException
     */
    public void browser(final URI uri) throws IOException {
        operations.browser(uri);
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void edit(final File file) throws IOException {
        operations.edit(file);
    }
}
