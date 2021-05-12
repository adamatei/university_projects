package fontys.sem3.group.sioux.repository.visitor;

import fontys.sem3.group.sioux.dalInterfaces.IVisitorDal;
import fontys.sem3.group.sioux.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VisitorDalJPA implements IVisitorDal {

    @Autowired
    IVisitorRepository repo;

    @Override
    public Visitor getVisitorByVisitorId(Long visitorId) {
        return repo.getVisitorByVisitorId(visitorId);
    }

    @Override
    public Visitor getFirstVisitorByLicencePlate(String licencePlate) {
        return repo.getFirstVisitorByLicencePlate(licencePlate);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return repo.findAll();
    }

    @Override
    public void addVisitor(Visitor visitor) {
        repo.save(visitor);
    }

    @Override
    public void deleteById(Long visitorId) {
        repo.deleteById(visitorId);
    }

    @Override
    public void save(Visitor visitor) {
        repo.save(visitor);
    }

}
