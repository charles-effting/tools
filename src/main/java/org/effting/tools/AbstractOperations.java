package org.effting.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 *
 * @author Charles Kafels Effting
 */
abstract class AbstractOperations implements Operations {

    @Override
    public void open(final File file) throws IOException {
        createExecutor(getOpenCommands(), file).execute();
    }

    @Override
    public void browser(URI uri) throws IOException {
        createExecutor(getBrowserCommands(), uri).execute();
    }

    @Override
    public void edit(File file) throws IOException {
        createExecutor(getEditCommands(), file).execute();
    }

    /**
     *
     * @return
     */
    protected abstract List<String> getOpenCommands();

    /**
     *
     * @return
     */
    protected abstract List<String> getBrowserCommands();

    /**
     *
     * @return
     */
    protected abstract List<String> getEditCommands();

    /**
     *
     * @param commands
     * @return
     */
    protected abstract <T> OperationsExecutor createExecutor(final List<String> commands, T argument);
}
