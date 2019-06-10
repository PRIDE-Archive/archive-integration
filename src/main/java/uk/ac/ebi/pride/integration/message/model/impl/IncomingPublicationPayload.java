package uk.ac.ebi.pride.integration.message.model.impl;

import org.springframework.util.Assert;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;
import uk.ac.ebi.pride.integration.message.model.PublicationPayload;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a approved publication request
 *
 * @author Rui Wang
 * @version $Id$
 */
public class IncomingPublicationPayload implements ProjectAccessionPayload, PublicationPayload, Serializable {

    private String accession;
    private String pubMedId;
    private Date publicationDate;

    public IncomingPublicationPayload() {
    }

    public IncomingPublicationPayload(String accession, Date publicationDate) {
        this(accession, null, publicationDate);
    }

    public IncomingPublicationPayload(String accession,
                                      String pubMedId,
                                      Date publicationDate) {
        Assert.notNull(accession, "Project accession cannot be null");
        Assert.notNull(publicationDate, "Project publication date cannot be null");

        this.accession = accession;
        this.pubMedId = pubMedId;
        this.publicationDate = publicationDate;
    }

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public String getPubMedId() {
        return pubMedId;
    }

    @Override
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomingPublicationPayload)) return false;

        IncomingPublicationPayload that = (IncomingPublicationPayload) o;

        if (!accession.equals(that.accession)) return false;
        if (pubMedId != null ? !pubMedId.equals(that.pubMedId) : that.pubMedId != null) return false;
        if (!publicationDate.equals(that.publicationDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession.hashCode();
        result = 31 * result + (pubMedId != null ? pubMedId.hashCode() : 0);
        result = 31 * result + publicationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IncomingPublicationPayload{" +
                "accession='" + accession + '\'' +
                ", pubMedId='" + pubMedId + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
