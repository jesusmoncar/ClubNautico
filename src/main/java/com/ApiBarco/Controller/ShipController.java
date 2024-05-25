//package com.ApiBarco.Controller;
//
//import com.ApiBarco.DTO.ShipDTO;
//
//import com.ApiBarco.Service.ShipService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/Ships")
//public class ShipController {
//
//    @Autowired
//    private ShipService shipService;
//
//    @GetMapping("{/id}")
//    public ShipDTO getShipById(@PathVariable long id) {
//        return shipService.getShipById(id);
//    }
//
//    @GetMapping
//    public List<ShipDTO> getAllShips() {
//        return shipService.getAllShip();
//    }
//
//    @PostMapping
//    public void addShip(@RequestBody ShipDTO shipDTO) {
//        shipService.createShip(shipDTO);
//    }
//
//
//
//}
