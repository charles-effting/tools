package org.effting.tools;

import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Charles Kafels Effting
 */
class OperationsExecutor {

    // Log.
    private static final Logger logger = LoggerFactory.getLogger(OperationsExecutor.class);

    // Kill the process after the determined time.
    private static final int PROCESS_TIMEOUT = 10; // seconds

    // The commands that the executor should try to execute.
    private final Iterator<String> commands;

    public OperationsExecutor(final List<String> commandList) {
        Objects.requireNonNull(commandList, "A list of commands must be informed.");

        this.commands = commandList.iterator();
    }

    /**
     * Append the arguments to the informed {@link CommandLine}. There's no order guarantee about which
     * command is going to be appended.
     * @param cmdLine a {@link CommandLine}.
     */
    protected void appendArguments(final CommandLine cmdLine) {
    }

    /**
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        final CommandLine cmdLine;
        synchronized (commands) {
            // If there are no commands left to execute, return and releases the lock.
            if (!commands.hasNext()) return;

            // Appends the arguments that are necessary to create the process.
            appendArguments(cmdLine = new CommandLine(commands.next()));
        }

        // Log.
        logger.debug("Command to be executed: {}", cmdLine);

        final Executor executor = new DefaultExecutor();
        executor.setWatchdog(new ExecuteWatchdog(TimeUnit.MILLISECONDS.convert(PROCESS_TIMEOUT, TimeUnit.SECONDS)));
        executor.execute(cmdLine, new OperationsResultHandler());
    }

    /**
     *
     */
    private class OperationsResultHandler implements ExecuteResultHandler {

        @Override
        public void onProcessComplete(int exitValue) {
            // Log.
            logger.info("Process exit value = {}", exitValue);
        }

        @Override
        public void onProcessFailed(ExecuteException executeException) {
            try {
                // Try to execute the next command.
                execute();

                // Log.
                logger.debug("Execution subprocess failed", executeException);
            } catch (IOException e) {
                // Log.
                logger.warn("Process failed", e);
            } finally {
                Thread.currentThread().interrupt();
            }
        }
    }

}
