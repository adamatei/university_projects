package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IVisitorDal;
import fontys.sem3.group.sioux.model.Visitor;
import fontys.sem3.group.sioux.serviceInterfaces.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VisitorService implements IVisitorService {

    IVisitorDal dal;
    @Autowired
    public VisitorService(IVisitorDal dal)
    {
        this.dal =dal;
    }

    @Override
    public Visitor getVisitorByVisitorId(Long visitorId) {
        return dal.getVisitorByVisitorId(visitorId);
    }

    @Override
    public Visitor getFirstVisitorByLicencePlate(String licencePlate) {
        return dal.getFirstVisitorByLicencePlate(licencePlate);
    }

    @Override
    public List<Visitor> getAllVisitors() { return dal.getAllVisitors(); }

    @Override
    public void addVisitor(Visitor visitor) {
        dal.addVisitor(visitor);
    }

    @Override
    public void deleteById(Long visitorId) {
        dal.deleteById(visitorId);
    }

    @Override
    public void save(Visitor visitor) {
        dal.save(visitor);
    }
}
