package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.ProductAuthor;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.repo.IProductAuthorRepo;
import sv.edu.ues.libues.service.IProductAuthorService;

@Service
public class ProductAuthorServiceImpl extends  CRUDImpl<ProductAuthor, Long> implements IProductAuthorService {

    @Autowired
    private IProductAuthorRepo repo;

    @Override
    protected IGenericRepo<ProductAuthor, Long> getRepo() {
        return repo;
    }
}