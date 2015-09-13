package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * A skeleton class to minimise the effort required to implemnt the {@link Operations} interface.
 *
 * @author Charles Kafels Effting
 */
abstract class AbstractOperations implements Operations {

    @Override
    public void open(final File file) throws IOException {
        Objects.requireNonNull(file, "'file' cannot be null");
        createExecutor(getOpenCommands(), file).execute();
    }

    @Override
    public void browser(final URI uri) throws IOException {
        Objects.requireNonNull(uri, "'uri' cannot be null");
        createExecutor(getBrowserCommands(), uri).execute();
    }

    @Override
    public void edit(final File file) throws IOException {
        Objects.requireNonNull(file, "'file' cannot be null");
        createExecutor(getEditCommands(), file).execute();
    }

    /**
     * Get a list of commands that should be executed by the {@link OperationsExecutor} to try to open a file or
     * directory.
     * @return a list of commands.
     */
    protected abstract List<String> getOpenCommands();

    /**
     * Get a list of commands that should be executed by the {@link OperationsExecutor} to try to open the default
     * web browser.
     * @return a list of commands.
     */
    protected abstract List<String> getBrowserCommands();

    /**
     * Get a list of commands that should be executed by the {@link OperationsExecutor} to try to edit the file.
     * @return a list of commands.
     */
    protected abstract List<String> getEditCommands();

    /**
     * A factory method which produces a new {@link OperationsExecutor} to execute a list of commands.
     * @param commands a list of commands.
     * @return the operations executor.
     */
    protected abstract <T> OperationsExecutor createExecutor(final List<String> commands, T argument);
}
