package org.effting.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

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
        final OperatingSystem os = OperatingSystem.current();
        assertEquals(os, OperatingSystem.UNIX);
    }

    @Test
    public void should_open_file_manager() throws IOException {
        final Operations operations = new UnixOperations();
        operations.open(new File(USER_HOME));

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
