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
 * A class that performs the execution of the defined commands by creating a new process. It uses the
 * class {@link Executor} provided by the {@code org.apache.commons:commons-exec} and wraps the configuration around
 * it.
 *
 * @author Charles Kafels Effting
 */
class OperationsExecutor {

    // Log.
    private static final Logger logger = LoggerFactory.getLogger(OperationsExecutor.class);

    // Kill the process after the determined time.
    private static final int PROCESS_TIMEOUT = 10; // seconds
    // The default value that indicates a normal exit is zero.
    private static final int DEFAULT_EXIT_CODE = 0;

    private int exitCode = DEFAULT_EXIT_CODE;

    // The commands that the executor should try to execute.
    private final Iterator<String> commands;

    /**
     * Creates a new {@link OperationsExecutor} to perform the command list.
     * @param commandList the list of commands.
     */
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
     * Sets the value that indicates that the process had a normal exit.
     * @param exitCode the code.
     */
    public void setExitCode(final int exitCode) {
        this.exitCode = exitCode;
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
        executor.setExitValue(exitCode);
        executor.execute(cmdLine, new OperationsResultHandler());
    }

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
