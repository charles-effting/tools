package org.effting.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Charles Kafels Effting
 */
@RunWith(JUnit4.class)
public class Desktop2Test {

    private static final String USER_HOME = System.getProperty("user.home");

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
    public void should_open_file_manager() throws IOException {
        final Operations operations = new UnixOperations();
        operations.open(new File(USER_HOME));

        try {
            Thread.sleep(TimeUnit.MILLISECONDS.convert(6, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
