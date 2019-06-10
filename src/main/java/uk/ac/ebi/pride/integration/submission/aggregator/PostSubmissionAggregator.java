package uk.ac.ebi.pride.integration.submission.aggregator;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ReleaseStrategy;
import uk.ac.ebi.pride.integration.message.model.IndexType;
import uk.ac.ebi.pride.integration.message.model.impl.IndexCompletionPayload;
import uk.ac.ebi.pride.integration.message.model.impl.SubmissionCompletionPayload;

import java.util.List;

/**
 * Aggregator implementation for combing post submission completion messages
 *
 * e.g. mzTab Indexing completion, mgf indexing completion
 *
 * @author Rui Wang
 * @version $Id$
 */
@MessageEndpoint
public class PostSubmissionAggregator {

    @Aggregator
    public SubmissionCompletionPayload aggregatingMethod(List<IndexCompletionPayload> payloads) {
        String accession = payloads.get(0).getAccession();

        return new SubmissionCompletionPayload(accession);
    }

    @ReleaseStrategy
    public boolean releaseChecker(List<IndexCompletionPayload> payloads) {
        boolean proteinIndexingCompleted = false;
        boolean psmIndexingCompleted = false;
        boolean spectrumIndexingCompleted = false;

        for (IndexCompletionPayload payload : payloads) {
            IndexType indexType = payload.getIndexType();
            if (indexType.equals(IndexType.SPECTRUM)) {
                spectrumIndexingCompleted = true;
            } else if (indexType.equals(IndexType.PROTEIN)) {
                proteinIndexingCompleted = true;
            } else if (indexType.equals(IndexType.PSM)) {
                psmIndexingCompleted = true;
            }
        }

        return proteinIndexingCompleted && psmIndexingCompleted && spectrumIndexingCompleted;
    }

    @CorrelationStrategy
    public String correlateBy(IndexCompletionPayload payload) {
        return payload.getAccession();
    }
}
