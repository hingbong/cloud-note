package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.Note;
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
  List<Note> findAllInANotebook(Integer nbId);

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
   * @return rows affected
   */
  Integer markIsDeleted(Integer nid);

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
   * @param nowNbId     now notebook id
   * @return rows affected
   */
  Integer moveNoteToDefaultNotebook(
      @Param("defaultNbId") Integer defaultNbId, @Param("nowNbId") Integer nowNbId);
}
