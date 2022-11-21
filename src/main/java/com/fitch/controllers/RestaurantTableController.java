package com.fitch.controllers;

import com.fitch.entities.MenuItem;
import com.fitch.entities.RestaurantTable;
import com.fitch.services.RestaurantTableService;
import com.fitch.services.RestaurantTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@CrossOrigin("*") //In case of CORS issues...
public class RestaurantTableController {


    //The table is just an object storing the table number. I have no endpoints here...

}
