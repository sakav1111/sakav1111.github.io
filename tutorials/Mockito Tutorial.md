
Mockito facilitates creating mock objects(sample , similar Objects). Mock
objects are nothing but proxy for actual implementations.

1.Creating Mock Objects
-----------------------

**1.Normal way**
```java
StockService stockServiceMock = mock(StockService.class);
```


**2.Using Annotation**
```java
@Mock
StockService stockServiceMock;
```


2.Methods & Usage
-----------------

All trhese methods are **static,** import provided by Mockito  
`import static org.mockito.Mockito.*;`

**when(...).thenReturn(...); -** adds a functionality to a mock object using the
methods when(). Then()
```java
when(...).thenReturn(...);
```


**// mock the behavior of stock service to return the value of various stocks**
```java
when(stockServiceMock.getPrice(googleStock)).thenReturn(50.00);
when(stockServiceMock.getPrice(microsoftStock)).thenReturn(1000.00);
```


```java
// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
	
   //@InjectMocks annotation is used to create and inject the mock object
   @InjectMocks //main Object 
   MathApplication mathApplication = new MathApplication();

   //@Mock annotation is used to create the dependent mock object 
   @Mock //Calculator object id decalted inside MathApplication
   CalculatorService calcService;

   @Test
   public void testAdd(){
      //add the behavior of calc service to add two numbers
      when(calcService.add(10.0,20.0)).thenReturn(30.00);
		
      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
   }
}
```

**verify()** method is ensure whether a mock method is being called with
reequired arguments or not
```java
//verify call to calcService is made or not with same arguments       verify(calcService).add(20.0, 30.0);
```


**Times()** method - Suppose MathApplication should call the
CalculatorService.serviceUsed() method only once
```java
//check if add function is called three times
verify(calcService, times(3)).add(10.0, 20.0);

//verify that method was never called on a mock
verify(calcService, never()).multiply(10.0,20.0)
```


Mockito provides the following additional methods to vary the expected call
counts
```java
//check a minimum 1 call count
verify(calcService, atLeastOnce()).subtract(20.0, 10.0);

//check if add function is called minimum 2 times
verify(calcService, atLeast(2)).add(10.0, 20.0);

//check if add function is called maximum 3 times
verify(calcService, atMost(3)).add(10.0,20.0);
```


### **Exception**  
```java
@Test(expected = RuntimeException.class)
   public void testAdd(){
      //add the behavior to throw exception
      doThrow(new RuntimeException("Add operation not implemented"))
         .when(calcService).add(10.0,20.0);
}
```


### **InOrder** class -
takes care of the order of method calls 
```java
//create an inOrder verifier for a single mock
InOrder inOrder = inOrder(calcService);

//following will make sure that add is first called then subtract is called.
inOrder.verify(calcService).subtract(20.0,10.0);
inOrder.verify(calcService).add(20.0,10.0);
```

### **reset** - 
reset a mock so that it can be reused later
```java
//reset the mock	  
reset(calcService);
```


[spy()](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Mockito.html#spy-T-)**/**[\@Spy](http://javadoc.io/page/org.mockito/mockito-core/latest/org/mockito/Spy.html)**:**
partial mocking, real methods are invoked but still can be verified and stubbed
