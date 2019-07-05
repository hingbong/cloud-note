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
   * @param uid user id
   * @param username username
   * @param note new note
   */
  void addNote(Integer uid, String username, Note note);

  /**
   * get notes of a notebook
   *
   * @param uid user id
   * @param nbId notebook id
   * @return notes of a notebook
   */
  List<Note> getNoteByNotebook(Integer uid, Integer nbId);

  /**
   * get all shared notes
   *
   * @return all shared notes
   */
  List<Note> getAllSharedNotes();

  /**
   * get a note by note id
   *
   * @param uid user id
   * @param nid note id
   * @return note
   */
  Note getNoteByNid(Integer uid, Integer nid);

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
   * @param uid user id
   * @param username modifying user
   * @param note note which is updated
   */
  void modifyNote(Integer uid, String username, Note note);

  /**
   * delete a note (mark)
   *
   * @param uid user id
   * @param username modifying user
   * @param nid note id which is updated
   */
  void deleteNote(Integer uid, String username, Integer nid);

  /**
   * set note to shared
   *
   * @param uid user id
   * @param username modifying user
   * @param nid note id which is updated
   */
  void setShared(Integer uid, String username, Integer nid);

  /**
   * set note to unshared
   *
   * @param uid user id
   * @param username modifying user
   * @param nid note id which is updated
   */
  void unsetShared(Integer uid, String username, Integer nid);

  /**
   * if delete a notebook, move all notes to default notebook
   *
   * @param defaultNbId default notebook id
   * @param nowNbId current notebook id
   */
  void moveToDefault(Integer defaultNbId, Integer nowNbId);
}
