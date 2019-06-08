package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.Notebook;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookMapper {

  Integer insert(Notebook notebook);

  Notebook findByTitle(String title);

  Notebook findByNbId(Integer nbId);

  List<Notebook> findAllByUid(Integer uid);

  Integer updateNotebook(@Param("title") String title, @Param("nbId") Integer nbId);

  Integer markDeleted(Integer nbId);

  Integer delete(Integer nbId);
}
