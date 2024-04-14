package pollub.myplanszeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pollub.myplanszeo.model.BoardGame;

import java.util.List;
import java.util.Optional;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {

    @Query("select b from BoardGame b join fetch b.category")
    List<BoardGame> findAllWithCategory();

    @Query("select b from BoardGame b join fetch b.category where b.id=?1")
    Optional<BoardGame> findByIdWithCategory(Long id);

}
