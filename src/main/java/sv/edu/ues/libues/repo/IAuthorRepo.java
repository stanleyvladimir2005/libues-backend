package sv.edu.ues.libues.repo;

import org.springframework.stereotype.Repository;
import sv.edu.ues.libues.model.Author;

@Repository
public interface IAuthorRepo extends IGenericRepo<Author, Long> {
}
