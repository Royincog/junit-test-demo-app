package com.application.testapp;

import com.application.testapp.dao.DaoService;
import com.application.testapp.dao.ReciveCakesDaoServiceImpl;
import com.application.testapp.services.CakeService;
import com.application.testapp.services.CakeServiceImpl;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DaoService daoService = new ReciveCakesDaoServiceImpl(new ArrayList<>());
        CakeService cakeService = new CakeServiceImpl(daoService);
        System.out.println(cakeService.filter((cake -> {
            return cake.getCakeFlavour().equals("Butter Scotch");
        })));

    }
}
