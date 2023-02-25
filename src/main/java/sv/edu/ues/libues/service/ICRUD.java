package sv.edu.ues.libues.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ICRUD<T,ID>  {

    T save (T t);

    T update (T t);

    List<T> findAll ();

    void delete (ID id);

    T findById(ID id);

    Page<T> listPageable(Pageable pageable);
}