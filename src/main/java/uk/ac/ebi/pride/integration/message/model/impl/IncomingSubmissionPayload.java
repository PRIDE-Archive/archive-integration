package uk.ac.ebi.pride.integration.message.model.impl;

import org.springframework.util.Assert;
import uk.ac.ebi.pride.archive.dataprovider.project.SubmissionType;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;
import uk.ac.ebi.pride.integration.message.model.SubmissionPayload;

import java.io.Serializable;
import java.util.Date;

/**
 * Incoming submission payload
 *
 * @author Rui Wang
 * @version $Id$
 */
public class IncomingSubmissionPayload implements ProjectAccessionPayload, SubmissionPayload, Serializable {
    private String accession;
    private SubmissionType submissionType;
    private Date submissionDate;

    public IncomingSubmissionPayload() {
    }

    public IncomingSubmissionPayload(String accession,
                                         SubmissionType submissionType,
                                         Date submissionDate) {
        Assert.notNull(accession, "Project accession cannot be null");
        Assert.notNull(submissionType, "Project submission type cannot be null");
        Assert.notNull(submissionDate, "Project submission date cannot be null");

        this.accession = accession;
        this.submissionType = submissionType;
        this.submissionDate = submissionDate;
    }

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public SubmissionType getSubmissionType() {
        return submissionType;
    }

    @Override
    public Date getSubmissionDate() {
        return submissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomingSubmissionPayload)) return false;

        IncomingSubmissionPayload that = (IncomingSubmissionPayload) o;

        if (!accession.equals(that.accession)) return false;
        if (!submissionDate.equals(that.submissionDate)) return false;
        if (submissionType != that.submissionType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession.hashCode();
        result = 31 * result + submissionType.hashCode();
        result = 31 * result + submissionDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IncomingSubmissionPayload{" +
                "accession='" + accession + '\'' +
                ", submissionType=" + submissionType +
                ", submissionDate=" + submissionDate +
                '}';
    }
}
