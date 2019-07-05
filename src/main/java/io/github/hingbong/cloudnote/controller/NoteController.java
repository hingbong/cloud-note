package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.service.NoteService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * note controller
 *
 * @author Hingbong
 */
@RestController
@RequestMapping("/note")
public class NoteController extends BaseController {

  private NoteService noteService;

  /**
   * add a note
   *
   * @param note new note
   * @return response
   */
  @PostMapping
  public JsonResponse<Void> addNote(Note note) {
    Integer uid = getUidFromSession();
    String username = getUsernameFromSession();
    noteService.addNote(uid, username, note);
    return JsonResponse.success("添加成功");
  }

  /**
   * get all shared notes
   *
   * @return response
   */
  @GetMapping("/notes/shared/all")
  public JsonResponse<List<Note>> getAllSharedNotes() {
    List<Note> noteList = noteService.getAllSharedNotes();
    return JsonResponse.success(noteList);
  }

  /**
   * get one shared note
   *
   * @param nid note id
   * @return response
   */
  @GetMapping("/shared/note/one/{nid}")
  public JsonResponse<Note> getSharedNoteByNid(@PathVariable Integer nid) {
    Note noteList = noteService.getNoteByNid(nid);
    return JsonResponse.success(noteList);
  }

  /**
   * get all notes in one notebook
   *
   * @param nbId notebook
   * @return response
   */
  @GetMapping("/{nbId}")
  public JsonResponse<List<Note>> getNotesByNbId(@PathVariable Integer nbId) {
    Integer uid = getUidFromSession();
    List<Note> notes = noteService.getNoteByNotebook(uid, nbId);
    return JsonResponse.success(notes);
  }

  /**
   * get one note
   *
   * @param nid note id
   * @return response
   */
  @GetMapping("/note/{nid}")
  public JsonResponse<Note> getNoteByNid(@PathVariable Integer nid) {
    Integer uid = getUidFromSession();
    Note note = noteService.getNoteByNid(uid, nid);
    return JsonResponse.success(note);
  }

  /**
   * update note
   *
   * @param note new note
   * @return response
   */
  @PutMapping
  public JsonResponse<Void> modifyNote(Note note) {
    Integer uid = getUidFromSession();
    String username = getUsernameFromSession();
    noteService.modifyNote(uid, username, note);
    return JsonResponse.success();
  }

  /**
   * change note sharing status to shared
   *
   * @param nid note id
   * @return response
   */
  @PutMapping("/{nid}/shared")
  public JsonResponse<Void> setShared(@PathVariable("nid") Integer nid) {
    String username = getUsernameFromSession();
    Integer uid = getUidFromSession();
    noteService.setShared(uid, username, nid);
    return JsonResponse.success();
  }

  /**
   * change note sharing status to unshared
   *
   * @param nid note id
   * @return response
   */
  @PutMapping("/{nid}/unshared")
  public JsonResponse<Void> unsetShared(@PathVariable("nid") Integer nid) {
    String username = getUsernameFromSession();
    Integer uid = getUidFromSession();
    noteService.unsetShared(uid, username, nid);
    return JsonResponse.success();
  }

  /**
   * delete a note
   *
   * @param nid note id
   * @return response
   */
  @DeleteMapping("/{nid}")
  public JsonResponse<Void> deleteNote(@PathVariable("nid") Integer nid) {
    String username = getUsernameFromSession();
    Integer uid = getUidFromSession();
    noteService.deleteNote(uid, username, nid);
    return JsonResponse.success();
  }

  @Autowired
  private void setNoteService(NoteService noteService) {
    this.noteService = noteService;
  }
}
