package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.Provider;
import sv.edu.ues.libues.repo.IProviderRepo;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.service.IProviderService;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider,Long> implements IProviderService {

    @Autowired
    private IProviderRepo repo;

    @Override
    protected IGenericRepo<Provider, Long> getRepo() {
        return repo;
    }
}