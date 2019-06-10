package uk.ac.ebi.pride.integration.submission.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import uk.ac.ebi.pride.integration.command.builder.CommandBuilder;
import uk.ac.ebi.pride.integration.command.builder.DefaultCommandBuilder;
import uk.ac.ebi.pride.integration.command.runner.CommandRunner;
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
public class MgfGenerationService {
    public static final Logger logger = LoggerFactory.getLogger(MgfGenerationService.class);

    @Autowired
    private CommandRunner mgfGenerationCommandRunner;

    @Value("#{commandConfig['command.mgf.generation.command']}")
    private String mgfGenerationCommand;

    @ServiceActivator
    public void generateMgfFiles(IncomingSubmissionPayload submission) {
        logger.info("Generating mgf files for project: " + submission.getAccession());

        CommandBuilder mgfGenerationCommandBuilder = new DefaultCommandBuilder();

        mgfGenerationCommandBuilder.argument(mgfGenerationCommand);
        // append project accession
        mgfGenerationCommandBuilder.argument("-a", submission.getAccession());
        // append notification argument
        mgfGenerationCommandBuilder.argument("-n");

        Collection<String> command = mgfGenerationCommandBuilder.getCommand();
        mgfGenerationCommandRunner.run(command);

    }

}
