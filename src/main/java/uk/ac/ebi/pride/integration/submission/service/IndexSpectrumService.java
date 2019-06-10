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
public class IndexSpectrumService {
    public static final Logger logger = LoggerFactory.getLogger(IndexSpectrumService.class);

    @Autowired
    private CommandRunner indexMgfCommandRunner;

    @Value("#{commandConfig['command.index.spectrum.command']}")
    private String indexSpectrumCommand;

    @ServiceActivator
    public void indexProject(FileGenerationPayload submission) {
        logger.info("Indexing mgf files for project: " + submission.getAccession());

        CommandBuilder indexMgfCommandBuilder = new DefaultCommandBuilder();

        indexMgfCommandBuilder.argument(indexSpectrumCommand);
        // append project accession
        indexMgfCommandBuilder.argument("-a", submission.getAccession());
        // append notification arguemnt
        indexMgfCommandBuilder.argument("-n");

        Collection<String> command = indexMgfCommandBuilder.getCommand();
        indexMgfCommandRunner.run(command);

    }
}
