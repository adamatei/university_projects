package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.model.Visitor;
import fontys.sem3.group.sioux.serviceInterfaces.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private IVisitorService service;

    //GET all the visitors from the database
    @GetMapping()
    //GET at http://localhost:XXXX/visitors
    public ResponseEntity<Iterable<Visitor>> getAllVisitors(){
        var visitors = service.getAllVisitors();
        if (visitors != null) {
            return ResponseEntity.ok().body(visitors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a visitor
    @PostMapping()
    //POST at http://localhost:XXXX/visitors/
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        service.addVisitor(visitor);
        //Create resource location
        var result = service.getVisitorByVisitorId((long) visitor.getId()).getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location).build();
    }

    //GET visitor by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/visitors/3
    public ResponseEntity<Visitor> getVisitorById(@PathVariable(value = "id") Long id) {
        var visitor = service.getVisitorByVisitorId(id);
        if(visitor != null){
            return ResponseEntity.ok().body(visitor);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //GET visitor by licencePlate
    @GetMapping("/licencePlate/{licencePlate}")
    //GET at http://localhost:XXXX/visitors/licencePlate/{licencePlate}
    public ResponseEntity<Visitor> getVisitorByLicensePlate(@PathVariable(value = "licencePlate") String licencePlate) {
        var visitor = service.getFirstVisitorByLicencePlate(licencePlate);
        if(visitor != null){
            return ResponseEntity.ok().body(visitor);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE visitor
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/visitors/
    public ResponseEntity<Visitor> updateVisitor(@PathVariable(value = "id") Long id, @RequestBody Visitor visitor){
        Visitor visitorData = service.getVisitorByVisitorId(id);

        if (visitorData != null) {
            Visitor updatedVisitor = visitorData;
            updatedVisitor.setFirstName(visitor.getFirstName());
            updatedVisitor.setLastName(visitor.getLastName());
            updatedVisitor.setLicencePlate(visitor.getLicencePlate());
            updatedVisitor.setPhone(visitor.getPhone());
            updatedVisitor.setDeleted(visitor.getDeleted());
            service.save(updatedVisitor);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE visitor
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/visitors/3
    public ResponseEntity<HttpStatus> removeVisitor(@PathVariable(value = "id") Long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //getting a list of appointments based on a visitor
//    @GetMapping("/appointments/{id}")
    //GET at http://localhost:XXXX/visitors/licencePlate/J534BT
//    public ResponseEntity<List<Appointment>> getAppointmentsByVisitor(@PathVariable(value = "id") int id) {
//        List<Appointment> appointments = visitorManagement.getAppointmentsByVisitor(id);
//        if(appointments != null){
//            return ResponseEntity.ok().body(appointments);
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }


//    //adding a new visitor to the list
//    @PostMapping()
//    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor){
//        if (!visitorManagement.addVisitor(visitor)) {
//            String entity = "Visitor with id " + visitor.getId() + " already exists.";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        } else {
//            String url = "visitors" + "/" + visitor.getId(); // url of the created visitor
//            URI uri = URI.create(url);
//            return new ResponseEntity(uri, HttpStatus.CREATED);
//        }
//    }

}