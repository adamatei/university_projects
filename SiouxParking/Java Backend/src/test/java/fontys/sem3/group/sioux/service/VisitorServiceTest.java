package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IVisitorDal;
import fontys.sem3.group.sioux.model.Visitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceTest {

    @InjectMocks
    VisitorService service;

    @Mock
    IVisitorDal repository;

    @Test
    void getAllVisitorsTest(){

        List<Visitor> visitors = new ArrayList<>();

        Visitor visitor1 = new Visitor(11L,"Axl", "Rose", "J206NT", "+31093382637", false, null);
        Visitor visitor2 = new Visitor(12L,"Axl2", "Rose2", "J695NT", "+31022382837", false, null);

        visitors.add(visitor1);
        visitors.add(visitor2);

        when(repository.getAllVisitors()).thenReturn(visitors);
        List<Visitor> retrievedVisitors = service.getAllVisitors();

        assertArrayEquals(visitors.toArray(),retrievedVisitors.toArray());
    }


    @Test
    void getFirstVisitorByLicencePlateTest(){

        Visitor visitor = new Visitor(11L,"Axl", "Rose", "J206NT", "+31093382637", false,null);

        when(repository.getFirstVisitorByLicencePlate("J206NT")).thenReturn(visitor);
        Visitor retrievedVisitor = service.getFirstVisitorByLicencePlate("J206NT");

        assertEquals(visitor,retrievedVisitor);
    }


    @Test
    void getVisitorByVisitorIdTest(){

        Visitor visitor = new Visitor(11L,"Axl", "Rose", "J206NT", "+31093382637", false,null);

        when(repository.getVisitorByVisitorId(111L)).thenReturn(visitor);
        Visitor retrievedVisitor = service.getVisitorByVisitorId(111L);

        assertEquals(visitor,retrievedVisitor);
    }


    @Test
    void addVisitorTest(){

        Visitor visitor = new Visitor(11L,"Axl", "Rose", "J206NT", "+31093382637", false,null);

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(visitor, arg0);
            return null;
        }).when(repository).addVisitor(any(Visitor.class));
        service.addVisitor(visitor);
    }

    @Test
    void deleteByIdTest(){

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(11L, arg0);
            return null;
        }).when(repository).deleteById(any(Long.class));
        service.deleteById(11L);
    }


    @Test
    void saveTest(){

        Visitor visitor = new Visitor(11L,"Axl", "Rose", "J206NT", "+31093382637", false,null);

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(visitor, arg0);
            return null;
        }).when(repository).save(any(Visitor.class));
        service.save(visitor);
    }

}

