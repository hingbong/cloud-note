package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.service.NoteSerivce;
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

@RestController
@RequestMapping("/note")
public class NoteController extends BaseController {

  private NoteSerivce noteSerivce;

  @PostMapping
  public JsonResponse<Void> addNote(HttpSession session, Note note) {
    Integer uid = getUidFromSeesion(session);
    noteSerivce.addNote(uid, note);
    return JsonResponse.response(SUCCESS, "添加成功", null);
  }

  @GetMapping("/{nbId}")
  public JsonResponse<List<Note>> getNotesByNbId(HttpSession session, @PathVariable Integer nbId) {
    Integer uid = getUidFromSeesion(session);
    List<Note> notes = noteSerivce.getNoteByNotebook(uid, nbId);
    return JsonResponse.response(SUCCESS, null, notes);
  }

  @GetMapping("/note/{nid}")
  public JsonResponse<Note> getNoteByNid(HttpSession session, @PathVariable Integer nid) {
    Integer uid = getUidFromSeesion(session);
    Note note = noteSerivce.getNoteByNid(uid, nid);
    return JsonResponse.response(SUCCESS, null, note);
  }

  @PutMapping
  public JsonResponse<Void> modifyNote(HttpSession session, Note note) {
    Integer uid = getUidFromSeesion(session);
    noteSerivce.modifyNote(uid, note);
    return JsonResponse.response(SUCCESS, null, null);
  }

  @Autowired
  public void setNoteSerivce(NoteSerivce noteSerivce) {
    this.noteSerivce = noteSerivce;
  }
}
