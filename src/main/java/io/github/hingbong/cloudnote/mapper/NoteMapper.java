package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.Note;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * mapper for t_note
 *
 * @author Hingbong
 */
@Repository
public interface NoteMapper {

  /**
   * insert a new note
   *
   * @param note new note
   * @return rows affected
   */
  Integer insert(Note note);

  /**
   * find all notes in a notebook
   *
   * @param nbId notebook id
   * @return all notes in a notebook
   */
  List<Note> findAllInOneNotebook(Integer nbId);

  /**
   * find all shared notes
   *
   * @return all shared notes
   */
  List<Note> findAllSharedNotes();

  /**
   * find a note by note id
   *
   * @param nid note id
   * @return note which is found
   */
  Note getOneByNid(Integer nid);

  /**
   * mark a note is deleted
   *
   * @param nid note id
   * @param modifiedUser user of modifying note
   * @param modifiedTime time of modifying note
   * @return rows affected
   */
  Integer markIsDeleted(
      @Param("nid") Integer nid,
      @Param("modifiedUser") String modifiedUser,
      @Param("modifiedTime") LocalDateTime modifiedTime);

  /**
   * change note to shared status
   *
   * @param nid note id
   * @param modifiedUser modifiedUser user of modifying note
   * @param modifiedTime modifiedTime time of modifying note
   * @return rows affected
   */
  Integer markIsShared(
      @Param("nid") Integer nid,
      @Param("modifiedUser") String modifiedUser,
      @Param("modifiedTime") LocalDateTime modifiedTime);

  /**
   * change note to unshared status
   *
   * @param nid nid note id
   * @param modifiedUser modifiedUser modifiedUser user of modifying note
   * @param modifiedTime modifiedTime time of modifying note
   * @return rows affected
   */
  Integer cancelShared(
      @Param("nid") Integer nid,
      @Param("modifiedUser") String modifiedUser,
      @Param("modifiedTime") LocalDateTime modifiedTime);

  /**
   * update a note
   *
   * @param note note of new data
   * @return rows affected
   */
  Integer updateNote(Note note);

  /**
   * when delete a notebook, move all notes to default notebook
   *
   * @param defaultNbId default notebook id
   * @param nowNbId now notebook id
   * @return rows affected
   */
  Integer moveNoteToDefaultNotebook(
      @Param("defaultNbId") Integer defaultNbId, @Param("nowNbId") Integer nowNbId);
}
