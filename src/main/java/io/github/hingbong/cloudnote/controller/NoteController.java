package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.service.NoteService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping
  public JsonResponse<Void> addNote(HttpSession session, Note note) {
    Integer uid = getUidFromSession(session);
    noteService.addNote(uid, note);
    return JsonResponse.response(SUCCESS, "添加成功", null);
  }

  @GetMapping("/{nbId}")
  public JsonResponse<List<Note>> getNotesByNbId(HttpSession session, @PathVariable Integer nbId) {
    Integer uid = getUidFromSession(session);
    List<Note> notes = noteService.getNoteByNotebook(uid, nbId);
    return JsonResponse.response(SUCCESS, null, notes);
  }

  @GetMapping("/note/{nid}")
  public JsonResponse<Note> getNoteByNid(HttpSession session, @PathVariable Integer nid) {
    Integer uid = getUidFromSession(session);
    Note note = noteService.getNoteByNid(uid, nid);
    return JsonResponse.response(SUCCESS, null, note);
  }

  @PutMapping
  public JsonResponse<Void> modifyNote(HttpSession session, Note note) {
    Integer uid = getUidFromSession(session);
    noteService.modifyNote(uid, note);
    return JsonResponse.response(SUCCESS, null, null);
  }

  @Autowired
  public void setNoteService(NoteService noteService) {
    this.noteService = noteService;
  }
}
