package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.Author;
import sv.edu.ues.libues.repo.IAuthorRepo;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.service.IAuthorService;

@Service
public class AuthorServiceImpl extends CRUDImpl<Author, Long> implements IAuthorService {

    @Autowired
    private IAuthorRepo repo;

    @Override
    protected IGenericRepo<Author, Long> getRepo() {
        return repo;
    }
}