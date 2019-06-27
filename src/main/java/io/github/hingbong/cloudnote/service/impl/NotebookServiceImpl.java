package io.github.hingbong.cloudnote.service.impl;

import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.mapper.NotebookMapper;
import io.github.hingbong.cloudnote.service.NoteService;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.service.UserService;
import io.github.hingbong.cloudnote.service.excption.DeleteDefaultNotebookException;
import io.github.hingbong.cloudnote.service.excption.DuplicateTitleException;
import io.github.hingbong.cloudnote.service.excption.FormatNotMatchException;
import io.github.hingbong.cloudnote.service.excption.InsertException;
import io.github.hingbong.cloudnote.service.excption.NotebookNotFoundException;
import io.github.hingbong.cloudnote.service.excption.UpdateException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement of NotebookService
 *
 * @author Hingbong
 */
@Service
public class NotebookServiceImpl implements NotebookService {

  private NotebookMapper notebookMapper;
  private UserService userService;
  private NoteService noteService;
  private static final String NULL = "null";

  @Override
  public void addNotebook(String title, String description, Integer uid) {
    checkNotebook(title);
    checkUser(uid);

    checkNotebookTitle(title, uid);

    Notebook notebook = new Notebook(title, description, 0, uid);
    insert(notebook);
  }

  @Override
  public void modifyTitle(Integer nbId, String title, Integer uid) {
    if (title == null || title.isEmpty() || NULL.equals(title)) {
      return;
    }
    checkNotebook(title);
    checkUserAndNotebook(uid, nbId);

    checkNotebookTitle(title, uid);

    updateTitle(nbId, title);
  }

  @Override
  public void modifyDescription(Integer nbId, String description, Integer uid) {
    if (description == null || description.isEmpty() || NULL.equals(description)) {
      return;
    }
    checkUserAndNotebook(uid, nbId);

    updateDescription(nbId, description);
  }

  @Override
  public List<Notebook> findAllByUid(Integer uid) {
    checkUser(uid);
    // get non-deleted notebook and set isDelete to null
    return selectAll(uid);
  }

  @Override
  public Notebook findByNbId(Integer nbId) {
    return getNotebook(nbId);
  }

  @Override
  public Notebook findByNbIdAndUid(Integer uid, Integer nbId) {
    Notebook notebook = getNotebook(nbId);
    if (notebook == null || notebook.getIsDeleted().equals(1) || !notebook.getUid().equals(uid)) {
      throw new NotebookNotFoundException("此记事本不存在");
    }
    notebook.setIsDeleted(null);
    return notebook;
  }

  private Notebook getNotebook(Integer nbId) {
    return notebookMapper.findByNbId(nbId);
  }

  @Override
  public void delete(Integer uid, Integer nbId) {
    checkUserAndNotebook(uid, nbId);
    Integer defaultNbId = checkDeleteDefault(nbId);
    delete(nbId);

    // when delete a notebook, move all notes to default
    noteService.moveToDefault(defaultNbId, nbId);
  }

  private Integer checkDeleteDefault(Integer nbId) {
    Notebook defaultNotebook = notebookMapper.findDefaultNotebook(nbId);
    if (nbId.equals(defaultNotebook.getNbId())) {
      throw new DeleteDefaultNotebookException("默认笔记本不能被删除");
    }
    return defaultNotebook.getNbId();
  }

  private List<Notebook> selectAll(Integer uid) {
    return notebookMapper.findAllByUid(uid).stream()
        .filter(notebook -> notebook.getIsDeleted().equals(0))
        .map(this::setIsDeletedToNull)
        .collect(Collectors.toList());
  }

  private void updateTitle(Integer nbId, String title) {
    Integer update = notebookMapper.updateNotebookTitle(title, nbId);
    if (!update.equals(1)) {
      throw new UpdateException("未知错误");
    }
  }

  private void updateDescription(Integer nbId, String description) {
    Integer update = notebookMapper.updateNotebookDescription(description, nbId);
    if (!update.equals(1)) {
      throw new UpdateException("未知错误");
    }
  }

  private void delete(Integer nbId) {
    Integer delete = notebookMapper.markDeleted(nbId);
    if (!delete.equals(1)) {
      throw new UpdateException("未知错误");
    }
  }

  private void checkNotebookTitle(String title, Integer uid) {
    Notebook byTitle = notebookMapper.findByTitle(title);
    if (byTitle != null && byTitle.getUid().equals(uid)) {
      throw new DuplicateTitleException("该标题已存在");
    }
  }

  private void insert(Notebook notebook) {
    Integer insert = notebookMapper.insert(notebook);
    if (!insert.equals(1)) {
      throw new InsertException("未知错误");
    }
  }

  private void checkUser(Integer uid) {
    userService.findByUid(uid);
  }

  private Notebook setIsDeletedToNull(Notebook notebook) {
    notebook.setIsDeleted(null);
    return notebook;
  }

  private void checkUserAndNotebook(Integer uid, Integer nbId) {
    if (nbId == null) {
      throw new FormatNotMatchException("记事本不能为空");
    }
    checkUser(uid);
    checkNotebook(nbId);
  }

  private void checkNotebook(Integer nbId) {
    Notebook byNbId = getNotebook(nbId);
    if (byNbId == null) {
      throw new NotebookNotFoundException("无此记事本");
    }
  }

  private void checkNotebook(String title) {
    if (title == null || "".equals(title)) {
      throw new FormatNotMatchException("格式不正确");
    }
  }

  @Autowired
  private void setNoteService(NoteService noteService) {
    this.noteService = noteService;
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  private void setNotebookMapper(NotebookMapper notebookMapper) {
    this.notebookMapper = notebookMapper;
  }
}
