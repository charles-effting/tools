package org.effting.tools;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 *
 * @author Charles Kafels Effting
 */
class UnixOperations implements Operations {

    protected static final String KDE_OPEN_COMMAND = "kde-open";
    protected static final String GNOME_OPEN_COMMAND = "gnome-open";
    protected static final String XDG_OPEN_COMMAND = "xdg-open";

    @Override
    public void open(final File file) throws IOException {
        new UnixOperationsExecutor<>(getOpenCommands(), file).execute();
    }

    @Override
    public void browser(URI uri) throws IOException {
        new UnixOperationsExecutor<>(getBrowserCommands(), uri.toString()).execute();
    }

    @Override
    public void edit(File file) throws IOException {
        new UnixOperationsExecutor<>(getEditCommands(), file).execute();
    }

    protected List<String> getOpenCommands() {
        return Collections.unmodifiableList(Arrays.asList(KDE_OPEN_COMMAND,
                GNOME_OPEN_COMMAND, XDG_OPEN_COMMAND));
    }

    protected List<String> getBrowserCommands() {
        // Use the open commands as they work to browse URIs as well.
        return getOpenCommands();
    }

    protected List<String> getEditCommands() {
        // Use the open commands as they work to edit files as well.
        return getOpenCommands();
    }

    /**
     *
     * @param <T>
     */
    protected static class UnixOperationsExecutor<T> extends OperationsExecutor {

        private final T argument;

        public UnixOperationsExecutor(final List<String> commandList, T argument) {
            super(commandList);

            this.argument = argument;
        }

        @Override
        protected void appendArguments(final CommandLine cmdLine) {
            cmdLine.addArgument("${argument}");

            final Map<String, T> substitutionMap = new HashMap<>();
            substitutionMap.put("argument", argument);
            cmdLine.setSubstitutionMap(substitutionMap);
        }
    }
}
