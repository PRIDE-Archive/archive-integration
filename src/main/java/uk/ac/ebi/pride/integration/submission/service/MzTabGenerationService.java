package uk.ac.ebi.pride.integration.submission.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import uk.ac.ebi.pride.integration.command.builder.CommandBuilder;
import uk.ac.ebi.pride.integration.command.builder.DefaultCommandBuilder;
import uk.ac.ebi.pride.integration.command.handler.DefaultCommandResultHandler;
import uk.ac.ebi.pride.integration.command.runner.CommandRunner;
import uk.ac.ebi.pride.integration.command.runner.DefaultCommandRunner;
import uk.ac.ebi.pride.integration.message.model.impl.IncomingSubmissionPayload;

import java.util.Collection;

/**
 * MzTabGenerationService is responsible for initiating mzTab generation for a submission
 *
 * NOTE: This service is lightweight service by calling external command
 *
 * @author Rui Wang
 * @version $Id$
 */
@MessageEndpoint
public class MzTabGenerationService {
    public static final Logger logger = LoggerFactory.getLogger(MzTabGenerationService.class);

    @Autowired
    private CommandRunner mzTabGenerationCommandRunner;

    @Value("#{commandConfig['command.mztab.generation.command']}")
    private String mzTabGenerationCommand;

    @ServiceActivator
    public void generateMzTabFiles(IncomingSubmissionPayload submission) {
        logger.info("Generating mzTab files for project: " + submission.getAccession());

        CommandBuilder mzTabGenerationCommandBuilder = new DefaultCommandBuilder();

        mzTabGenerationCommandBuilder.argument(mzTabGenerationCommand);
        // append project accession
        mzTabGenerationCommandBuilder.argument("-a", submission.getAccession());
        // append notification argument
        mzTabGenerationCommandBuilder.argument("-n");

        Collection<String> command = mzTabGenerationCommandBuilder.getCommand();
        mzTabGenerationCommandRunner.run(command);
    }

}
