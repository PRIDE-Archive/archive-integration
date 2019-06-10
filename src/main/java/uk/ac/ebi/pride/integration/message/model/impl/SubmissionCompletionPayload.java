package uk.ac.ebi.pride.integration.message.model.impl;

import org.springframework.util.Assert;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;

import java.io.Serializable;

/**
 * Submission completion payload
 *
 * @author Rui Wang
 * @version $Id$
 */
public class SubmissionCompletionPayload implements ProjectAccessionPayload,Serializable {

    private String accession;

    public SubmissionCompletionPayload() {
    }

    public SubmissionCompletionPayload(String accession) {
        Assert.notNull(accession, "Project accession cannot be null");

        this.accession = accession;
    }

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmissionCompletionPayload)) return false;

        SubmissionCompletionPayload that = (SubmissionCompletionPayload) o;

        if (!accession.equals(that.accession)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return accession.hashCode();
    }

    @Override
    public String toString() {
        return "SubmissionCompletionPayload{" +
                "accession='" + accession + '\'' +
                '}';
    }
}
