package com.phalaenopsis.phalaenopsis.auditory;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
//@InjectMocks anotacijata se koristi za kreiranje I injektiranje na mock objektot
@InjectMocks
MathApplication mathApplication = new MathApplication();
//@Mock anotacijata se koristi za da se kreira mock objektot koj treba da bide injektiran
@Mock
CalculatorService calcService;
@Test
public void testAdd(){
// dodadi go odnesuvanjeto na calc servisot za da se soberat ovie dva broevi
when(calcService.add(10.0,20.0)).thenReturn(30.00);
//testiraj ja ADD funkcionalnosta
Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
}
}