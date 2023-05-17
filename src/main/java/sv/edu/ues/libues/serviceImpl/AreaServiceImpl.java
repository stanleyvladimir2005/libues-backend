package sv.edu.ues.libues.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.ues.libues.model.Area;
import sv.edu.ues.libues.repo.IAreaRepo;
import sv.edu.ues.libues.repo.IGenericRepo;
import sv.edu.ues.libues.service.IAreaService;

@Service
public class AreaServiceImpl extends CRUDImpl<Area,Long> implements IAreaService {

    @Autowired
    private IAreaRepo repo;

    @Override
    protected IGenericRepo<Area, Long> getRepo() {
        return repo;
    }
}