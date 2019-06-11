package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.Notebook;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * mapper for t_notebook
 *
 * @author Hingbong
 */
@Repository
public interface NotebookMapper {

  /**
   * insert a new notebook
   *
   * @param notebook new notebook
   * @return rows affected
   */
  Integer insert(Notebook notebook);

  /**
   * find a notebook by title
   *
   * @param title notebook title
   * @return notebook, if no notebook of this title, return null
   */
  Notebook findByTitle(String title);

  /**
   * find a notebook by notebook id
   *
   * @param nbId notebook id
   * @return notebook, if no notebook of this title, return null
   */
  Notebook findByNbId(Integer nbId);

  /**
   * get a user's all notebook
   *
   * @param uid user id
   * @return a user's all notebook
   */
  List<Notebook> findAllByUid(Integer uid);

  /**
   * update notebook(notebook title)
   *
   * @param title notebook new title
   * @param nbId  notebook id
   * @return rows affected
   */
  Integer updateNotebook(@Param("title") String title, @Param("nbId") Integer nbId);

  /**
   * mark a notebook as deleted(fake delete)
   *
   * @param nbId notebook id
   * @return rows affected
   */
  Integer markDeleted(Integer nbId);

  /**
   * delete a notebook
   *
   * @param nbId notebook id
   * @return rows affected
   */
  Integer delete(Integer nbId);
}
