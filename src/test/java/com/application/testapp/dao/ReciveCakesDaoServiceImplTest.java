package com.application.testapp.dao;


import com.application.testapp.entity.Cake;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class ReciveCakesDaoServiceImplTest {

    @InjectMocks
    private ReciveCakesDaoServiceImpl reciveCakesDaoService;
    @Mock
    private List<Cake> listOfCakes;


    @Test
    public void testRetriveListOfCakes() {
        lenient().when(listOfCakes.get(anyInt())).thenReturn(new Cake(3,"Coffee Cake","coffee"));
        assertEquals("Coffee Cake",reciveCakesDaoService.retriveListOfCakes().get(100).getCakeName());
    }
}