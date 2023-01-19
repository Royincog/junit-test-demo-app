package com.application.testapp.services;
import com.application.testapp.dao.DaoService;
import com.application.testapp.entity.Cake;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class}) //We can use mockito dependencies in class
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CakeServiceImplTest {

      @InjectMocks  //Works only with Impl class and injects all the mocks with @Mock annotation
      private CakeServiceImpl cakeService;


   // @Mock
   // private CakeService cakeService;

    @Mock
    private DaoService daoService;

    @Mock
    private List<Cake> testcakeMockList;




    @BeforeEach
    void init(){
        testcakeMockList = new ArrayList<>();




}
    @Test //Test annotation for each unit Test
    @DisplayName("This test checks the Cake Service filter with flavour")
    void testCakeServiceFilterWithFlavour(){
        //Create some Cakes for the Test Case
        testcakeMockList.add(new Cake(3,"Coffee Cake","coffee"));
        testcakeMockList.add(new Cake(4,"Baked Fruit Cake","mixedfruit"));

        //return cakes mocks as wish
        lenient().when(daoService.retriveListOfCakes()).thenReturn(testcakeMockList);
      //  cakeService = new CakeServiceImpl(daoService); // put the dao Service Mock in cake Service


         assertNotNull(testcakeMockList);  //Null check for the list we are
         assertFalse(testcakeMockList.isEmpty()); //Assert a False
         assertEquals(Cake.class,cakeService.filterWithFlavour("coffee").get(0).getClass()); //Check the insance of the class
         //assertEquals("coffee",cakeService.filterWithFlavour("mixedfruit").get(0).getCakeFlavour());


        // System.out.println(cakeService.filterWithFlavour("coffee"));

    }

@Test
void test_the_CakeService_FilterWithFlavour_with_Null_Cake(){
    //Create some Cakes for the Test Case
    testcakeMockList.add(new Cake()); //Empty Cake Object
    //Let's mock out some list ofanything
    lenient().when(daoService.retriveListOfCakes()).thenReturn(testcakeMockList);
    cakeService = new CakeServiceImpl(daoService);

    assertNotNull(testcakeMockList);
    assertFalse(testcakeMockList.isEmpty());

    //Scope of improvement  -> in actual method please verify the returned list containing any null value and then filter.

    assertThrows(NullPointerException.class,()->{
        cakeService.filterWithFlavour("coffee").get(anyInt()).getCakeName();
    });


}
@Test
void test_cakeName_filter(){
        List<Cake> cakesMockList = spy(ArrayList.class);
        Cake cake = new Cake(1,"Spice cake","spice");

        cakesMockList.add(cake);
        verify(cakesMockList).add(cake); //verify that the same instance of cake being added
        verify(cakesMockList, VerificationModeFactory.times(1)).add(cake); //verify how many times added


    lenient().when(daoService.retriveListOfCakes()).thenReturn(cakesMockList);
    cakeService = new CakeServiceImpl(daoService);

    assertEquals(new Cake(1,"Spice cake","spice"),cakeService.filterWithName("Spice cake").get(0),"Not the same instance");
   // System.out.println();

  //  System.out.println(cakesMockList.get(0));
}

@Test
    void Test_Cakes_WIth_Assumptions(){
        lenient().when(daoService.retriveListOfCakes()).thenReturn(null); //let say we are getting null response
        Assumptions.assumeTrue(daoService.retriveListOfCakes() != null); //wrong assumptions will halt the test case :)

    }
@Test
@Tag("TestwithID")
    void Test_Cakes_With_ID(){

        Cake bananaCake = new Cake(7,"Banana Cake","Banana");
        testcakeMockList.add(bananaCake);
    //Same stuff
    lenient().when(daoService.retriveListOfCakes()).thenReturn(testcakeMockList);
    cakeService = new CakeServiceImpl(daoService);

    //Converting list to arrays
    Cake[] cakes = cakeService.filterWithId(7).toArray(new Cake[0]);
    Cake[] testCake = new Cake[]{bananaCake};

    assertArrayEquals(testCake,cakes,"check two arrays based on number passed to filter"); //check arrays
}

@RepeatedTest(2)
    void repeat_the_test_with_generalFilter(RepetitionInfo repetitionInfo){
        System.out.println(repetitionInfo.getCurrentRepetition());
        lenient().when(daoService.retriveListOfCakes()).thenReturn(null);
        //cakeService = new CakeServiceImpl(daoService);
        doReturn(null).when(cakeService).filter(any(Predicate.class));
        assertNull(cakeService.filter((cake)->true));
}



    @Nested
    @DisplayName("New Test Cases")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestSomethingNew{

        private Cake cake;
        @BeforeAll
        void beforeAllInit(TestInfo testInfo){
            this.cake = new Cake();
            System.out.println(cake.hashCode());
            System.out.println(testInfo.getTestClass().get());
            System.out.println("Some init in Before all");
        }

        @ParameterizedTest
        @ValueSource(strings = {"coffee","chocolate"})
        void check_flavours_with_parameters(String flavours){

            List<Cake> someCakes = Arrays.asList(new Cake(3,"Coffee Cake","coffee"),new Cake(1,"Spice cake","spice"));

            lenient().when(daoService.retriveListOfCakes()).thenReturn(someCakes);
            cakeService = new CakeServiceImpl(daoService);

            //assertEquals("coffee",cakeService.filterWithFlavour(flavours).get(0).getCakeFlavour());
            assertThrows(IndexOutOfBoundsException.class,() -> cakeService.filterWithFlavour(flavours).get(0),"Only get thrown when there is index out of bound");
           // System.out.println(cakeService.filterWithFlavour(flavours));
        }

//        @AfterAll
//        void afterEach(){
//            System.out.println(cake.hashCode());
//        }


}

}