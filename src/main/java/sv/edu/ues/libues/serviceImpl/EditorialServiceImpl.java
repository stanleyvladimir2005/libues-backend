package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.Editorial;
import sv.edu.ues.libues.repo.IEditorialRepo;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.service.IEditorialService;

@Service
public class EditorialServiceImpl extends CRUDImpl<Editorial,Long> implements IEditorialService {

    @Autowired
    private IEditorialRepo repo;

    @Override
    protected IGenericRepo<Editorial, Long> getRepo() {
        return repo;
    }
}