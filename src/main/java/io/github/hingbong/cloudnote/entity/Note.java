package io.github.hingbong.cloudnote.entity;

import java.util.Objects;

/**
 * t_note table
 *
 * @author Hingbong
 */
public class Note {

  private Integer nid;
  private String title;
  private String content;
  private Integer isShared;
  private Integer isDeleted;
  private Integer nbId;

  public Integer getNid() {
    return nid;
  }

  public void setNid(Integer nid) {
    this.nid = nid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getIsShared() {
    return isShared;
  }

  public void setIsShared(Integer isShared) {
    this.isShared = isShared;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Note note = (Note) o;
    return Objects.equals(nid, note.nid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nid);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Note{");
    sb.append("nid=").append(nid);
    sb.append(", title='").append(title).append('\'');
    sb.append(", content='").append(content).append('\'');
    sb.append(", isShared=").append(isShared);
    sb.append(", isDeleted=").append(isDeleted);
    sb.append(", nbId=").append(nbId);
    sb.append('}');
    return sb.toString();
  }
}
