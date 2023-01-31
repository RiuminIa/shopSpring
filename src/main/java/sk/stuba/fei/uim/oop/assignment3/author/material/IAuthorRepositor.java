package sk.stuba.fei.uim.oop.assignment3.author.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepositor extends JpaRepository<Author,Long> {
}
