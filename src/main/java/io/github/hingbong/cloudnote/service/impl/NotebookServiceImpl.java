package io.github.hingbong.cloudnote.service.impl;

import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.entity.User;
import io.github.hingbong.cloudnote.mapper.NotebookMapper;
import io.github.hingbong.cloudnote.mapper.UserMapper;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.service.excption.DuplicateTitleException;
import io.github.hingbong.cloudnote.service.excption.FormatNotMatchException;
import io.github.hingbong.cloudnote.service.excption.InsertException;
import io.github.hingbong.cloudnote.service.excption.NotebookNotFoundException;
import io.github.hingbong.cloudnote.service.excption.UpdateException;
import io.github.hingbong.cloudnote.service.excption.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotebookServiceImpl implements NotebookService {

  private NotebookMapper notebookMapper;
  private UserMapper userMapper;

  @Override
  public void addNotebook(String title, Integer uid) {
    checkNotebook(title);
    checkUser(uid);

    chekTitle(title, uid);

    Notebook notebook = new Notebook(title, 0, uid);
    insert(notebook);
  }


  @Override
  public void modifyTitle(Integer nbId, String title, Integer uid) {
    checkNotebook(title);
    checkUserAndNotebook(uid, nbId);

    checkTitle(title, uid);

    update(nbId, title);
  }

  @Override
  public List<Notebook> findAllByUid(Integer uid) {
    checkUser(uid);
    // get non-deleted notebook and set isDelete to null
    return selectAll(uid);
  }


  @Override
  public void delete(Integer uid, Integer nbId) {
    checkUserAndNotebook(uid, nbId);
    delete(nbId);
  }

  private List<Notebook> selectAll(Integer uid) {
    return notebookMapper.findAllByUid(uid).stream()
        .filter(notebook -> notebook.getIsDeleted().equals(0))
        .map(this::setIsDeletedToNull)
        .collect(Collectors.toList());
  }

  private void update(Integer nbId, String title) {
    Integer update = notebookMapper.updateNotebook(title, nbId);
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

  private void chekTitle(String title, Integer uid) {
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
    User user = userMapper.findByUid(uid);
    if (user == null) {
      throw new UserNotFoundException("用户未登录");
    }
  }

  private void checkTitle(String title, Integer uid) {
    chekTitle(title, uid);
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
    checkNotebok(nbId);
  }

  private void checkNotebok(Integer nbId) {
    Notebook byNbId = notebookMapper.findByNbId(nbId);
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
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Autowired
  public void setNotebookMapper(NotebookMapper notebookMapper) {
    this.notebookMapper = notebookMapper;
  }
}
