package pollub.myplanszeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pollub.myplanszeo.model.BoardGame;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
}
