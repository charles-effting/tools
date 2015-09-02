package org.effting.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Charles Kafels Effting
 */
@RunWith(JUnit4.class)
public class Desktop2Test {

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String EDIT_FILE = "edit.txt";

    @Test
    public void should_be_linux_os() {
        System.setProperty("os.name", "Linux Mint 17");

        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.UNIX);
    }

    @Test
    public void should_be_windows_os() {
        System.setProperty("os.name", "Microsoft Windows 7");

        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.WINDOWS);
    }

    @Test
    public void should_be_mac_os() {
        System.setProperty("os.name", "Apple Macintosh");

        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.MAC_OS);
    }

    @Test
    public void should_be_solaris_os() {
        System.setProperty("os.name", "Sun Solaris 1");

        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.UNIX);
    }

    @Test
    public void should_be_unknown_os() {
        System.setProperty("os.name", "Random Operating System");

        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.UNKNOWN);
    }

    @Test
    public void should_show_user_home_directory() throws IOException {
        // A fresh file manager should be opened.
        Desktop2.getDesktop().open(new File(USER_HOME));

        // Wait a few seconds to allow the process execution.
        sleep(6);
    }

    @Test
    public void should_open_browser() throws IOException {
        // The default browser should be opened.
        Desktop2.getDesktop().browser(URI.create("https://github.com/"));

        // Wait a few seconds to allow the process execution.
        sleep(6);
    }

    @Test
    public void should_edit_file() throws IOException {
        // The default editor text should be opened.
        Desktop2.getDesktop().edit(new File(getClass().getResource(EDIT_FILE).getPath()));

        // Wait a few seconds to allow the process execution.
        sleep(6);
    }

    private void sleep(final int seconds) {
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
