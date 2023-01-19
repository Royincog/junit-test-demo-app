package com.application.testapp.services;

import com.application.testapp.dao.DaoService;
import com.application.testapp.entity.Cake;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CakeServiceImpl implements CakeService{
    private DaoService daoService;
    public CakeServiceImpl(DaoService daoService){
        this.daoService = daoService;
    }

    @Override
    public List<Cake> filter(Predicate<Cake> predicate) {
        return daoService.retriveListOfCakes()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cake> filterWithFlavour(String flavourName) {
         return  daoService.retriveListOfCakes()
                .stream()
                .filter((cake -> cake.getCakeFlavour().equalsIgnoreCase(flavourName)))
                .collect(Collectors.toList());

    }

    @Override
    public List<Cake> filterWithName(String CakeName) {
        return  daoService.retriveListOfCakes()
                .stream()
                .filter((cake -> cake.getCakeName().equalsIgnoreCase(CakeName)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cake> filterWithId(int Id) {
        return  daoService.retriveListOfCakes()
                .stream()
                .filter((cake -> cake.getCakeId() == Id))
                .collect(Collectors.toList());
    }

}
