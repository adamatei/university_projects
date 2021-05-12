package fontys.sem3.group.sioux.dalInterfaces;

import fontys.sem3.group.sioux.model.Visitor;

import java.util.List;

public interface IVisitorDal {
    Visitor getVisitorByVisitorId (Long visitorId);
    Visitor getFirstVisitorByLicencePlate(String licencePlate);
    List<Visitor> getAllVisitors();
    void addVisitor(Visitor visitor);
    void deleteById(Long visitorId);
    void save(Visitor visitor);
}

