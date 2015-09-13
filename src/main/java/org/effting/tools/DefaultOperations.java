package org.effting.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * A fallback that uses the {@link java.awt.Desktop} when the operating system
 * is unknown.
 *
 * @author Charles Kafels Effting
 */
class DefaultOperations implements Operations {

    // Log.
    private static final Logger logger = LoggerFactory.getLogger(DefaultOperations.class);

    /**
     * A class that just log the lack of support when its methods are called.
     */
    static class UnsupportedDesktop implements Operations {

        @Override
        public void open(final File file) {
            // Log.
            logger.info("Desktop implementation not support by the current platform.");
        }

        @Override
        public void browser(final URI uri) {
            // Log.
            logger.info("Desktop implementation not support by the current platform.");
        }

        @Override
        public void edit(final File file) {
            // Log.
            logger.info("Desktop implementation not support by the current platform.");
        }
    }

    /**
     * A class that delegates the operations to the {@link Desktop} implementation.
     */
    static class SupportedDesktop implements Operations {

        private final Desktop desktop = Desktop.getDesktop();

        @Override
        public void open(final File file) throws IOException {
            desktop.open(file);
        }

        @Override
        public void browser(final URI uri) throws IOException {
            desktop.browse(uri);
        }

        @Override
        public void edit(final File file) throws IOException {
            desktop.edit(file);
        }
    }

    private final Operations desktopOperations;

    public DefaultOperations() {
        // Check and instanciate an implementation based on the support of the java.awt.Desktop class.
        boolean desktopSupported = Desktop.isDesktopSupported();

        // Log.
        logger.info("Desktop support for current platform: {}", desktopSupported);

        if (desktopSupported) {
            desktopOperations = new SupportedDesktop();
        } else {
            desktopOperations = new UnsupportedDesktop();
        }
    }

    @Override
    public void open(final File file) throws IOException {
        desktopOperations.open(file);
    }

    @Override
    public void browser(final URI uri) throws IOException {
        desktopOperations.browser(uri);
    }

    @Override
    public void edit(final File file) throws IOException {
        desktopOperations.edit(file);
    }
}
