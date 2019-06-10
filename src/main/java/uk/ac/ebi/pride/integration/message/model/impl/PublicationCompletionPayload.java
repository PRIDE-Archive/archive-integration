package uk.ac.ebi.pride.integration.message.model.impl;

import org.springframework.util.Assert;
import uk.ac.ebi.pride.integration.message.model.ProjectAccessionPayload;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Suresh Hewapathirana
 */
public class PublicationCompletionPayload implements ProjectAccessionPayload,Serializable {

  private String accession;

  public PublicationCompletionPayload() {
  }

  public PublicationCompletionPayload(String accession) {
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
    if (o == null || getClass() != o.getClass()) return false;
    PublicationCompletionPayload that = (PublicationCompletionPayload) o;
    return Objects.equals(accession, that.accession);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accession);
  }

  @Override
  public String toString() {
    return "PublicationCompletionPayload{" +
            "accession='" + accession + '\'' +
            '}';
  }
}
