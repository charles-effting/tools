package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * A class which defines some methods that are provided by the {@link java.awt.Desktop} implementation. Its
 * primary purpose is to offer better alternatives to solve known issues with the default implementation
 * such as opening a file or directory in operating systems other than Windows.
 *
 * @author Charles Kafels Effting
 */
public class Desktop2 {

    // Singleton instance.
    private static final Desktop2 INSTANCE = new Desktop2();

    private final Operations operations;

    // Suppresses default constructor, ensuring non-instantiability.
    private Desktop2() {
        // Gets the underlying implementation based on the current operating system.
        operations = OperationsFactory.create();
    }

    /**
     * Gets the singleton instance of {@link Desktop2}.
     * @return the instance.
     */
    public static Desktop2 getDesktop() {
        return INSTANCE;
    }

    /**
     * Launches the associated application to open the file.
     *
     * <p> If the specified file is a directory, the file manager of
     * the current platform is launched to open it.</p>
     *
     * This method is asynchronous and returns immediately to the caller.
     * @param file the file to be opened with the associated application.
     * @throws NullPointerException if the specified file is {@code null}
     * @throws IOException if the specified file has no associated
     * application or the associated application fails to be launched.
     */
    public void open(final File file) throws IOException {
        operations.open(file);
    }

    /**
     * Launches the default browser to display a {@code URI}.
     *
     * This method is asynchronous and returns immediately to the caller.
     * @param uri the URI to be displayed in the user default browser
     * @throws NullPointerException if the specified file is {@code null}
     * @throws IOException if the user default browser is not found,
     * or it fails to be launched
     */
    public void browser(final URI uri) throws IOException {
        operations.browser(uri);
    }

    /**
     * Launches the associated editor application and opens a file for
     * editing.
     *
     * This method is asynchronous and returns immediately to the caller.
     * @param file the file to be opened for editing
     * @throws NullPointerException if the specified file is {@code null}
     * @throws IOException if the specified file has no associated
     * editor, or the associated application fails to be launched
     */
    public void edit(final File file) throws IOException {
        operations.edit(file);
    }
}
