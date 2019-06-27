package io.github.hingbong.cloudnote.entity;

import java.time.LocalDateTime;
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
  private String author;
  private LocalDateTime createTime;
  private String modifiedUser;
  private LocalDateTime modifiedTime;

  public Integer getNid() {
    return nid;
  }

  public Note setNid(Integer nid) {
    this.nid = nid;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Note setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public Note setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getIsShared() {
    return isShared;
  }

  public Note setIsShared(Integer isShared) {
    this.isShared = isShared;
    return this;
  }

  public Integer getIsDeleted() {
    return isDeleted;
  }

  public Note setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
    return this;
  }

  public Integer getNbId() {
    return nbId;
  }

  public Note setNbId(Integer nbId) {
    this.nbId = nbId;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public Note setAuthor(String author) {
    this.author = author;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public Note setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getModifiedUser() {
    return modifiedUser;
  }

  public Note setModifiedUser(String modifiedUser) {
    this.modifiedUser = modifiedUser;
    return this;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public Note setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
    return this;
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
    return "Note{"
        + "nid="
        + nid
        + ", title='"
        + title
        + '\''
        + ", content='"
        + content
        + '\''
        + ", isShared="
        + isShared
        + ", isDeleted="
        + isDeleted
        + ", nbId="
        + nbId
        + ", author='"
        + author
        + '\''
        + ", createTime="
        + createTime
        + ", modifiedUser='"
        + modifiedUser
        + '\''
        + ", modifiedTime="
        + modifiedTime
        + '}';
  }
}
