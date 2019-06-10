package uk.ac.ebi.pride.integration.submission.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;
import uk.ac.ebi.pride.archive.dataprovider.project.SubmissionType;
import uk.ac.ebi.pride.integration.message.model.SubmissionPayload;

/**
 * Complete or pride only submission filter
 *
 * @author Rui Wang
 * @version $Id$
 */
@MessageEndpoint
public class CompleteOrPrideSubmissionTypeFilter {
    public static final Logger logger = LoggerFactory.getLogger(CompleteOrPrideSubmissionTypeFilter.class);

    @Filter
    public boolean isCompleteOrPrideSubmission(SubmissionPayload submissionPayload) {
        logger.info("Filtering for complete or pride submissions: " + submissionPayload.getSubmissionType());

        SubmissionType submissionType = submissionPayload.getSubmissionType();

        return SubmissionType.COMPLETE.equals(submissionType) || SubmissionType.PRIDE.equals(submissionType);
    }
}
