package io.github.hingbong.cloudnote.service.impl;

import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.mapper.NoteMapper;
import io.github.hingbong.cloudnote.service.NoteService;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.service.excption.InvalidNoteException;
import io.github.hingbong.cloudnote.service.excption.NoteNotFoundException;
import io.github.hingbong.cloudnote.service.excption.NotebookNotFoundException;
import io.github.hingbong.cloudnote.service.excption.UpdateException;
import io.github.hingbong.cloudnote.service.excption.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement of NoteService
 *
 * @author Hingbong
 */
@Service
public class NoteServiceImpl implements NoteService {

  private NoteMapper noteMapper;
  private NotebookService notebookService;

  @Override
  public void addNote(Integer uid, Note note) {
    checkNote(note);
    Notebook notebook = notebookService.findByNbId(note.getNbId());
    checkNotebook(uid, note, notebook);
    note.setIsShared(0);
    note.setIsDeleted(0);

    noteMapper.insert(note);
  }

  @Override
  public List<Note> getNoteByNotebook(Integer uid, Integer nbId) {
    if (nbId == null) {
      throw new NotebookNotFoundException("无此记事本");
    }
    Notebook notebook = notebookService.findByNbId(nbId);
    if (notebook == null) {
      throw new NotebookNotFoundException("无此记事本");
    }
    if (!notebook.getUid().equals(uid)) {
      throw new UserNotFoundException("请选择正确的记事本");
    }
    List<Note> notes = noteMapper.findAll(nbId);
    return notes.stream()
        .filter(note -> note.getIsDeleted().equals(0))
        .map(this::setIsDeletedToNull)
        .collect(Collectors.toList());
  }

  @Override
  public Note getNoteByNid(Integer uid, Integer nid) {
    return getOneByNid(uid, nid);
  }

  @Override
  public void modifyNote(Integer uid, Note note) {
    if (note.getNbId() != null) {
      Notebook notebook = notebookService.findByNbId(note.getNbId());
      if (!uid.equals(notebook.getUid())) {
        throw new UserNotFoundException("请选择正确的笔记");
      }
    }
    if (note.getNid() == null) {
      throw new NoteNotFoundException("错误的笔记");
    }
    Note note1 = noteMapper.getOneByNid(note.getNid());
    if (note1 == null) {
      throw new NoteNotFoundException("错误的笔记");
    }
    note.setIsShared(note1.getIsShared());
    note.setIsDeleted(0);
    Integer updateNote = noteMapper.updateNote(note);
    if (!updateNote.equals(1)) {
      throw new UpdateException("未知错误");
    }
  }

  private Note getOneByNid(Integer uid, Integer nid) {
    if (nid == null) {
      throw new NoteNotFoundException("无此笔记");
    }
    Note note = noteMapper.getOneByNid(nid);
    if (note == null) {
      throw new NoteNotFoundException("无此笔记");
    }
    Notebook notebook = notebookService.findByNbId(note.getNbId());
    if (!uid.equals(notebook.getUid())) {
      throw new UserNotFoundException("请选择正确的笔记");
    }
    return setIsDeletedToNull(note);
  }

  private void checkNote(Note note) {
    if (note == null) {
      throw new InvalidNoteException("笔记不能为空");
    }
    if (note.getNbId() == null) {
      throw new InvalidNoteException("请选择记事本");
    }
    if (note.getTitle() == null) {
      throw new InvalidNoteException("笔记标题不能为空");
    }
    if (note.getContent() == null) {
      throw new InvalidNoteException("笔记内容不能为空");
    }
  }

  private void checkNotebook(Integer uid, Note note, Notebook notebook) {
    if (notebook == null) {
      throw new NotebookNotFoundException("无此记事本");
    }
    if (note.getNbId().equals(0)) {
      throw new NotebookNotFoundException("请选择正确的记事本");
    }
    if (!notebook.getUid().equals(uid)) {
      throw new UserNotFoundException("请选择正确的记事本");
    }
  }

  private Note setIsDeletedToNull(Note note) {
    note.setIsDeleted(null);
    return note;
  }

  @Autowired
  private void setNotebookService(NotebookService notebookService) {
    this.notebookService = notebookService;
  }

  @Autowired
  private void setNoteMapper(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }
}
