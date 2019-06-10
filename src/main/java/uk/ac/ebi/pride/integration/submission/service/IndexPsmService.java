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
import uk.ac.ebi.pride.integration.message.model.impl.FileGenerationPayload;

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
public class IndexPsmService {
    public static final Logger logger = LoggerFactory.getLogger(IndexPsmService.class);

    @Autowired
    private CommandRunner indexMzTabCommandRunner;

    @Value("#{commandConfig['command.index.psm.command']}")
    private String indexPsmCommand;

    @ServiceActivator
    public void indexProject(FileGenerationPayload submission) {
        logger.info("Indexing PSMs for project: " + submission.getAccession());

        CommandBuilder indexMzTabCommandBuilder = new DefaultCommandBuilder();

        indexMzTabCommandBuilder.argument(indexPsmCommand);
        // append project accession
        indexMzTabCommandBuilder.argument("-a", submission.getAccession());
        // append notification argument
        indexMzTabCommandBuilder.argument("-n");

        Collection<String> command = indexMzTabCommandBuilder.getCommand();
        indexMzTabCommandRunner.run(command);

    }
}
