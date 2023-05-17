package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.ProductType;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.repo.IProductTypeRepo;
import sv.edu.ues.libues.service.IProductTypeService;

@Service
public class ProductTypeServiceImpl extends CRUDImpl<ProductType,Long> implements IProductTypeService {

    @Autowired
    private IProductTypeRepo repo;

    @Override
    protected IGenericRepo<ProductType, Long> getRepo() {
        return repo;
    }
}