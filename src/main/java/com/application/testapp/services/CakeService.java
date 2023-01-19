package com.application.testapp.services;

import com.application.testapp.entity.Cake;

import java.util.List;
import java.util.function.Predicate;

public interface CakeService {

    public List<Cake> filter(Predicate<Cake> predicate);
    public List<Cake> filterWithFlavour(String flavourName);
    public List<Cake> filterWithName(String CakeName);

    public List<Cake> filterWithId(int Id);


}
