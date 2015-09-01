package org.effting.tools;

import org.apache.commons.exec.CommandLine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Charles Kafels Effting
 */
class MacOperations extends AbstractOperations {

    protected static final String OPEN_COMMAND = "open";

    @Override
    protected List<String> getOpenCommands() {
        return Collections.unmodifiableList(Collections.singletonList(OPEN_COMMAND));
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
        return new MacOperationsExecutor<>(commands, argument);
    }

    /**
     *
     * @param <T>
     */
    protected static class MacOperationsExecutor<T> extends OperationsExecutor {

        private final T argument;

        public MacOperationsExecutor(final List<String> commandList, T argument) {
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
