package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author Charles Kafels Effting
 */
interface Operations {

    /**
     *
     * @param file
     * @return
     */
    void open(File file) throws IOException;

    /**
     *
     * @param uri
     * @return
     */
    void browser(URI uri) throws IOException;

    /**
     *
     * @param file
     * @return
     */
    void edit(File file) throws IOException;
}
