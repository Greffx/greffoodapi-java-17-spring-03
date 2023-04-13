package com.greff.foodapi.api.controller; //api package is to controllers and more other types
//Resource Representation domain

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
//This annotation is to be recognized as 'controller' and is responsible to receive web request from user, treat them, with services layers and return an answer,
//'rest' means that answer should be delivered as a correct answer of http request, without will not work, 'rest' got @ResponseBody annotation inside
@RequestMapping("/kitchens")
//controller needs to be mapped, those requests need to found their right controller, this annotation does it
public class KitchenController {

    private final KitchenService kitchenService; //injection of instance of kitchen service, with the interface type, to be less coupled as possible,

    //always good to inject interfaces than coupled classes, but if there is no other way, do with the simple type
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping
    //need to map method, getMapping means that requests with verb http 'GET' will use this method
    public ResponseEntity<List<Kitchen>> findAll() { //this is called 'endpoint', with methods like GET, POST, PUT and REMOVE
        //this can be called as a collection of resources, that means is something that is exposed in web
        //resource to be reached, need to be id by a URI, we need a URL to request, using http protocol
        return ResponseEntity.ok(kitchenService.findAll());
    }

    @GetMapping("/{id}") //This is specification, we need this because can't have 2 endpoints with same path.
    //problem with ambiguous endpoints with same URL path "/{can be any other name in here}", that is a placeholder and will bind with our method param 'id'
    //with annotation '@PathVariable', means that will be required to have that id and will do correctly binding part
    //this can be called as a singleton resource, that means is something that is exposed in web
    public ResponseEntity<Kitchen> getKitchen(@PathVariable Long id) { //ResponseEntity represents HTTP response, which can have an instance of object of some type, ResponseEntity<Type>, can add Headers and modify Http status
        return ResponseEntity.ok(kitchenService.findById(id)); //it is a builder, has some methods, it means that we can build our response, like ok() return 200 ok status http.
        //inside (here) is the body response, with that response got payload singleton response or could be a collection resource
    }

    @GetMapping("/search/") //Mapping method to getMethod, adding one more path to URI
    //This param will not be received by Variable path, will come as Query param, can be null, so RequestParam make binding with var, self-explained, like ?name=<nameOfYourChoice>
    //RequestParam is optional in here, when is query param bind is auto, you can remove it, if you want it
    public ResponseEntity<List<Kitchen>> getKitchenByName(@RequestParam String name) {
        return ResponseEntity.ok(kitchenService.findByName(name));
    }

    @GetMapping("/search/exists")
    public ResponseEntity<Boolean> getKitchenIfExists(@RequestParam String name) {
        return ResponseEntity.ok(kitchenService.findByIfExistsName(name));
    }

    @PostMapping
    //map method, PostMapping means that requests with verb http 'POST' will use this method, will create obj
    public ResponseEntity<Kitchen> create(@RequestBody Kitchen kitchen, UriComponentsBuilder builder) {
        //annotation RequestBody means that param 'kitchen' will receive body of request, transformation of body JSON and bind with 'kitchen' instance
        Kitchen kitchen1 = kitchenService.addKitchen(kitchen);
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(kitchen1.getId()).toUri()).body(kitchen1);
    }

    @PutMapping("/{id}")
    //map method, PutMapping means that requests with verb http 'PUT' will use this method, will update obj based on id
    public ResponseEntity<Kitchen> update(@RequestBody Kitchen kitchen, @PathVariable Long id) {
        return ResponseEntity.ok(kitchenService.updateKitchen(kitchen, id));
    }

    @DeleteMapping("/{id}")
    //map method, DeleteMapping means that requests with verb http 'DELETE' will use this method, will delete obj based on id
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            kitchenService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
