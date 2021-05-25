package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.repository.ConcertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

    @Mock
    ConcertRepository repository;

    @InjectMocks
    ConcertService service;

    @Test
    public void getConcerts(){
        List<Concert> concerts = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        String str2="24-07-2021";
        Date date2 = null;
        try {
            date2 = formatter.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c2 = new Concert(2, "Estadio Benito Villamarin", "Spain","Seville", date2);

        String str3="04-09-2021";
        Date date3 = null;
        try {
            date3 = formatter.parse(str3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c3 = new Concert(3, "Sweden Rock Festival", "Sweden","Solvesborg", date3);

        concerts.add(c1);
        concerts.add(c2);
        concerts.add(c3);

        when(repository.findAll()).thenReturn(concerts);
        CompletableFuture<List<Concert>> completableFuture = service.getConcerts();
        List<Concert> actualConcerts = null;
        try {
            actualConcerts = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(concerts.toArray(), actualConcerts.toArray());
    }


    @Test
    public void getConcertById(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(c1));
        CompletableFuture<Concert> completableFuture = service.getConcertById(1);
        Concert actualConcert = null;
        try {
            actualConcert = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c1, actualConcert);
    }

    @Test
    public void saveConcert(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        when(repository.save(c1)).thenReturn(c1);
        CompletableFuture<Concert> completableFuture = service.saveConcert(c1);
        Concert actualConcert = null;
        try {
            actualConcert = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c1, actualConcert);
    }

    @Test
    public void updateConcert(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String str="31-06-2021";
        Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Concert c1 = new Concert(1, "Passeio Martimo De Alges", "Portugal","Lisbon", date1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(c1));
        when(repository.save(c1)).thenReturn(c1);
        CompletableFuture<Concert> completableFuture = service.updateConcert(c1);
        Concert actualConcert = null;
        try {
            actualConcert = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(c1, actualConcert);
    }
}
