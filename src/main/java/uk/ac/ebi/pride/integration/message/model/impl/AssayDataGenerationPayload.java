package uk.ac.ebi.pride.integration.message.model.impl;

import org.springframework.util.Assert;
import uk.ac.ebi.pride.integration.message.model.AssayAccessionPayload;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;

public class AssayDataGenerationPayload implements ProjectAccessionPayload, AssayAccessionPayload {

    String projectAccession;
    String assayAccession;

    public AssayDataGenerationPayload(String projectAccession, String assayAccession) {
        Assert.notNull(projectAccession, "Project accession cannot be null");
        Assert.notNull(assayAccession, "Assay accession cannot be null");

        this.projectAccession = projectAccession;
        this.assayAccession = assayAccession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssayDataGenerationPayload that = (AssayDataGenerationPayload) o;

        if (!projectAccession.equals(that.projectAccession)) return false;
        return assayAccession.equals(that.assayAccession);
    }

    @Override
    public int hashCode() {
        int result = projectAccession.hashCode();
        result = 31 * result + assayAccession.hashCode();
        return result;
    }

    @Override
    public String getAssayAccession() {
        return assayAccession;
    }

    @Override
    public String getAccession() {
        return projectAccession;
    }
}
