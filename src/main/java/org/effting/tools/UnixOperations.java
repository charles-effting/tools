package org.effting.tools;

import org.apache.commons.exec.CommandLine;

import java.util.*;

/**
 * The implementation to perform operations on UNIX based operating systems.
 *
 * @author Charles Kafels Effting
 */
class UnixOperations extends AbstractOperations {

    protected static final String KDE_OPEN_COMMAND = "kde-open";
    protected static final String GNOME_OPEN_COMMAND = "gnome-open";
    protected static final String XDG_OPEN_COMMAND = "xdg-open";

    @Override
    protected List<String> getOpenCommands() {
        return Collections.unmodifiableList(Arrays.asList(KDE_OPEN_COMMAND,
                GNOME_OPEN_COMMAND, XDG_OPEN_COMMAND));
    }

    @Override
    protected List<String> getBrowserCommands() {
        // Use the open commands as they work to browse URIs as well.
        return getOpenCommands();
    }

    @Override
    protected List<String> getEditCommands() {
        // Use the open commands as they work to edit files as well.
        return getOpenCommands();
    }

    @Override
    protected <T> OperationsExecutor createExecutor(final List<String> commands, final T argument) {
        return new UnixOperationsExecutor<>(commands, argument);
    }

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
