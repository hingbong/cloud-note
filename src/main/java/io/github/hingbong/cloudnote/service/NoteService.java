package io.github.hingbong.cloudnote.service;

import io.github.hingbong.cloudnote.entity.Note;
import java.util.List;

/**
 * note service
 *
 * @author Hingbong
 */
public interface NoteService {

  /**
   * add a new note
   *
   * @param uid  user id
   * @param note new note
   */
  void addNote(Integer uid, Note note);

  /**
   * get notes of a notebook
   *
   * @param uid user id
   * @param nbId notebook id
   * @return notes of a notebook
   */
  List<Note> getNoteByNotebook(Integer uid, Integer nbId);

  /**
   * get a note by note id
   *
   * @param uid user id
   * @param nid note id
   * @return note
   */
  Note getNoteByNid(Integer uid, Integer nid);

  /**
   * update note
   *
   * @param uid user id
   * @param note note which is updated
   */
  void modifyNote(Integer uid, Note note);
}
