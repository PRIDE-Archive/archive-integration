package uk.ac.ebi.pride.integration.message.model.impl;

import uk.ac.ebi.pride.integration.message.model.FileType;
import uk.ac.ebi.pride.integration.message.model.FileTypePayload;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;

import java.io.Serializable;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class FileGenerationPayload implements ProjectAccessionPayload, FileTypePayload, Serializable {

    private String accession;

    private FileType fileType;

    public FileGenerationPayload() {
    }

    public FileGenerationPayload(String accession, FileType fileType) {
        this.accession = accession;
        this.fileType = fileType;
    }

    public String getAccession() {
        return accession;
    }

    public FileType getFileType() {
        return fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileGenerationPayload)) return false;

        FileGenerationPayload that = (FileGenerationPayload) o;

        if (!accession.equals(that.accession)) return false;
        if (fileType != that.fileType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession.hashCode();
        result = 31 * result + fileType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FileGenerationPayload{" +
                "accession='" + accession + '\'' +
                ", fileType=" + fileType +
                '}';
    }
}
