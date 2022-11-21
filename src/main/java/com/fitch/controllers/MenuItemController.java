package com.fitch.controllers;

import com.fitch.entities.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.fitch.services.MenuItemService;
import com.fitch.services.MenuItemServiceImpl;

import java.awt.*;
import java.util.List;

@Component
@RestController
@CrossOrigin("*") //In case of CORS issues...
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService = new MenuItemServiceImpl();

    // GET endpoint to return the full list of available menu items
    @GetMapping("/items")
    @ResponseBody
    public List<MenuItem> getMenuItems() {
        return this.menuItemService.getMenuItems();
    }
}
