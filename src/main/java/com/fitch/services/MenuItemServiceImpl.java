package com.fitch.services;

import com.fitch.entities.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.fitch.repositories.MenuItemRepository;

import java.util.List;

@Component
@Service
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    private MenuItemRepository menuItemRepository;


    //This gets the full list of menu items to display to the customer
    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> allItems = menuItemRepository.findAll();
        return allItems;
    }
}
