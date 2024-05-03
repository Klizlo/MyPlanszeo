package pollub.myplanszeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pollub.myplanszeo.model.BoardGame;
import pollub.myplanszeo.model.BoardGameList;
import pollub.myplanszeo.model.User;

import java.util.List;
import java.util.Optional;

public interface MyPlanszeoRepository {

    @Query("select b from BoardGameList b where b.user.id=?1")
    List<BoardGameList> findAllByUser_Id(Long userId);
    @Query("select b from BoardGameList b where b.id=?1 and b.user.id=?2")
    boolean existsByIdAndUser_Id(Long boardGameListId, Long userId);

    @Query("select u from User u where u.email=?1")
    Optional<User> findByEmail(String email);

    @Query("select b from BoardGame b join fetch b.category")
    List<BoardGame> findAllWithCategory();

    @Query("select b from BoardGame b join fetch b.category where b.id=?1")
    Optional<BoardGame> findByIdWithCategory(Long id);
}
