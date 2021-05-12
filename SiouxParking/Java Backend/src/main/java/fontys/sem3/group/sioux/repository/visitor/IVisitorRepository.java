package fontys.sem3.group.sioux.repository.visitor;

import fontys.sem3.group.sioux.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitorRepository extends JpaRepository<Visitor, Long> {
    Visitor getVisitorByVisitorId(Long visitorId);
    Visitor getFirstVisitorByLicencePlate(String licencePlate);
    void deleteByVisitorId(Long visitorId);
}
