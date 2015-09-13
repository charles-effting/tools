package org.effting.tools;

import org.apache.commons.exec.CommandLine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation to perform operations on Windows operating systems.
 *
 * @author Charles Kafels Effting
 */
class WindowsOperations extends AbstractOperations {

    protected static final String EXPLORER_COMMAND = "explorer.exe";

    @Override
    protected List<String> getOpenCommands() {
        return Collections.unmodifiableList(Collections.singletonList(EXPLORER_COMMAND));
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
        return new WindowsOperationsExecutor<>(commands, argument);
    }

    protected static class WindowsOperationsExecutor<T> extends OperationsExecutor {

        private static final int DEFAULT_EXIT_CODE = 1;

        private final T argument;

        public WindowsOperationsExecutor(final List<String> commandList, T argument) {
            super(commandList);

            this.argument = argument;

            // Sets the default exit code for Window processes.
            setExitCode(DEFAULT_EXIT_CODE);
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
