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
   * @param note new note
   */
  void addNote(Note note);

  /**
   * get notes of a notebook
   *
   * @param nbId notebook id
   * @return notes of a notebook
   */
  List<Note> getNoteByNotebook(Integer nbId);

  /**
   * get all shared notes
   *
   * @return all shared notes
   */
  List<Note> getAllSharedNotes();

  /**
   * get a note by note id
   *
   * @param nid note id
   * @return note
   */
  Note getNoteByNidAndCurrentUser(Integer nid);

  /**
   * get a note by note id
   *
   * @param nid note id
   * @return note
   */
  Note getNoteByNid(Integer nid);

  /**
   * update note
   *
   * @param note note which is updated
   */
  void modifyNote(Note note);

  /**
   * delete a note (mark)
   *
   * @param nid note id which is updated
   */
  void deleteNote(Integer nid);

  /**
   * set note to shared
   *
   * @param nid note id which is updated
   */
  void setShared(Integer nid);

  /**
   * set note to unshared
   *
   * @param nid note id which is updated
   */
  void unsetShared(Integer nid);

  /**
   * if delete a notebook, move all notes to default notebook
   *
   * @param defaultNbId default notebook id
   * @param nowNbId current notebook id
   */
  void moveToDefault(Integer defaultNbId, Integer nowNbId);
}
