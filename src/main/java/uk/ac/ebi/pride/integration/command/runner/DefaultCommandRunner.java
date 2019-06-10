package uk.ac.ebi.pride.integration.command.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import uk.ac.ebi.pride.integration.command.handler.CommandResultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * String based command runner which the command represented as a string
 *
 * @author Rui Wang
 * @version $Id$
 */
public class DefaultCommandRunner implements CommandRunner{
    public static final Logger logger = LoggerFactory.getLogger(DefaultCommandRunner.class);

    private CommandResultHandler commandResultHandler;

    public DefaultCommandRunner(CommandResultHandler commandResultHandler) {
        Assert.notNull(commandResultHandler, "Command result handler cannot be null");

        this.commandResultHandler = commandResultHandler;
    }

    public void run(Collection<String> command) throws CommandRunnerException {
        Assert.notNull(command, "Input command cannot be null");

        try {
            executeCommand(command);
        } catch (InterruptedException e) {
            String msg = "Command interrupted: " + command;
            logger.error(msg, e);
            throw new CommandRunnerException(msg, e);
        } catch (IOException e) {
            String msg = "Failed to start command: " + command;
            logger.error(msg, e);
            throw new CommandRunnerException(msg, e);
        }
    }

    private void executeCommand(Collection<String> commands) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(new ArrayList<String>(commands));

        // run command
        Process process = processBuilder.start();
        process.waitFor();

        // handle command results
        commandResultHandler.handle(process);
    }
}
