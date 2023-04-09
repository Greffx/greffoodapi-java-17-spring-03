package com.greff.foodapi.api.controller; //api package is to controllers and more other types

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.usecase.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//This annotation is to be recognized as 'controller' and is responsible to receive web request from user, treat them, with services layers and return an answer,
// 'rest' means that answer should be delivered as a correct answer of http request, without will not work, 'rest' got @ResponseBody annotation inside
@RestController
@RequestMapping("/kitchens") //controller needs to be mapped, those requests need to found their right controller, this annotation does it
public class KitchenController {

    private final KitchenService kitchenService; //injection of kitchen service, with the interface type, to be less coupled as possible,
                                                 // always good to inject interfaces than coupled classes, but if there is no other way, do with the simple type

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })//need to map method, getMapping means that requests with verb http 'GET' will use this method
                                                                   //'produces' means this method produces a specific format
                                                                   //can use this produces at RequestMapping, will be at every method of class
    public ResponseEntity<List<Kitchen>> getList() { //this is called 'endpoint', with methods like GET, POST, PUT and REMOVE
                                                     //this can be called as a collection of resources, that means is something that is exposed in web
                                                     //resource to be reached, need to be id by a URI, we need a URL to request, using http protocol
        return ResponseEntity.ok().body(kitchenService.findAll());
    }
}
