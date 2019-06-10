package uk.ac.ebi.pride.integration.submission.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import uk.ac.ebi.pride.integration.message.model.impl.IncomingSubmissionPayload;
import uk.ac.ebi.pride.integration.message.model.impl.SubmissionCompletionPayload;

/**
 * Message endpoint to transform incoming submission into submission complete message
 *
 * @author Rui Wang
 * @version $Id$
 */
@MessageEndpoint
public class SubmissionCompleteMessageTransformer {
    public static final Logger logger = LoggerFactory.getLogger(SubmissionCompletionPayload.class);

    @Transformer
    public SubmissionCompletionPayload transform(IncomingSubmissionPayload incomingSubmissionPayload) {
        String accession = incomingSubmissionPayload.getAccession();
        logger.info("Transform incoming submission into submission completion message: accession {}, type {}", accession, incomingSubmissionPayload.getSubmissionType());
        return new SubmissionCompletionPayload(accession);
    }
}
