package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.Product;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.repo.IProductRepo;
import sv.edu.ues.libues.service.IProductService;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Long> implements IProductService {

    @Autowired
    private IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Long> getRepo() {
        return repo;
    }
}