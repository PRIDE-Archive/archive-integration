package uk.ac.ebi.pride.integration.message.model.impl;

import uk.ac.ebi.pride.integration.message.model.IndexType;
import uk.ac.ebi.pride.integration.message.model.IndexTypePayload;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;

import java.io.Serializable;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class IndexCompletionPayload implements ProjectAccessionPayload, IndexTypePayload, Serializable {

    private String accession;

    private IndexType indexType;

    public IndexCompletionPayload() {
    }

    public IndexCompletionPayload(String accession, IndexType indexType) {
        this.accession = accession;
        this.indexType = indexType;
    }

    public String getAccession() {
        return accession;
    }

    public IndexType getIndexType() {
        return indexType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexCompletionPayload)) return false;

        IndexCompletionPayload that = (IndexCompletionPayload) o;

        if (!accession.equals(that.accession)) return false;
        if (indexType != that.indexType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession.hashCode();
        result = 31 * result + indexType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IndexCompletionPayload{" +
                "accession='" + accession + '\'' +
                ", fileType=" + indexType +
                '}';
    }
}
