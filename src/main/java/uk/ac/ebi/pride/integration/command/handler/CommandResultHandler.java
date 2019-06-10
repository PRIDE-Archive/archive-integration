package uk.ac.ebi.pride.integration.command.handler;

/**
 * CommandResultHandler provides a strategy to handle command results
 *
 * @author Rui Wang
 * @version $Id$
 */
public interface CommandResultHandler {

    /**
     * Handle the result of a process
     *
     * @param p given process
     */
    public void handle(Process p);
}
