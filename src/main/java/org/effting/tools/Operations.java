package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * The contract that represents the available operations performed by the underlying operating system.
 *
 * @author Charles Kafels Effting
 */
interface Operations {

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
    void open(File file) throws IOException;

    /**
     * Launches the default browser to display a {@code URI}.
     *
     * This method is asynchronous and returns immediately to the caller.
     * @param uri the URI to be displayed in the user default browser
     * @throws NullPointerException if the specified file is {@code null}
     * @throws IOException if the user default browser is not found,
     * or it fails to be launched
     */
    void browser(URI uri) throws IOException;

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
    void edit(File file) throws IOException;
}
