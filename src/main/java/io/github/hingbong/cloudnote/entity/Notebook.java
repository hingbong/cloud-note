package io.github.hingbong.cloudnote.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * t_notebook table
 *
 * @author Hingbong
 */
public class Notebook implements Serializable {

  private Integer nbId;
  private String title;
  private String description;
  private Integer isDeleted;
  private Integer uid;

  public Notebook() {
  }

  public Notebook(String title, String description, Integer isDeleted, Integer uid) {
    this.title = title;
    this.description = description;
    this.isDeleted = isDeleted;
    this.uid = uid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notebook notebook = (Notebook) o;
    return nbId.equals(notebook.nbId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nbId);
  }

  public Integer getNbId() {
    return nbId;
  }

  public Notebook setNbId(Integer nbId) {
    this.nbId = nbId;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Notebook setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Notebook setDescription(String description) {
    this.description = description;
    return this;
  }

  public Integer getIsDeleted() {
    return isDeleted;
  }

  public Notebook setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
    return this;
  }

  public Integer getUid() {
    return uid;
  }

  public Notebook setUid(Integer uid) {
    this.uid = uid;
    return this;
  }

  @Override
  public String toString() {
    return "Notebook{"
        + "nbId="
        + nbId
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", isDeleted="
        + isDeleted
        + ", uid="
        + uid
        + '}';
  }
}
