package pollub.myplanszeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pollub.myplanszeo.model.BoardGameList;

import java.util.List;

@Repository
public interface BoardGameListRepository extends JpaRepository<BoardGameList, Long> {

    List<BoardGameList> findAllByUser_Id(Long userId);
    boolean existsByIdAndUser_Id(Long boardGameListId, Long userId);
}
