package io.github.hingbong.cloudnote.entity;

import java.io.Serializable;
import java.util.Objects;

public class Notebook implements Serializable {

  private Integer nbId;
  private String title;
  private Integer isDeleted;
  private Integer uid;

  public Notebook() {
  }

  public Notebook(String title, Integer isDeleted, Integer uid) {
    this.title = title;
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

  public Integer getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getNbId() {
    return nbId;
  }

  public void setNbId(Integer nbId) {
    this.nbId = nbId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Notebook{");
    sb.append("nbId=").append(nbId);
    sb.append(", title='").append(title).append('\'');
    sb.append(", uid=").append(uid);
    sb.append('}');
    return sb.toString();
  }
}
