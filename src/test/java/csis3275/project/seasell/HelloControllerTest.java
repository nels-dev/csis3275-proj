package csis3275.project.seasell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

  @Test
  public void sayHelloTest(){
    HelloController homeController = new HelloController();
    assertEquals("Hi!!!!", homeController.sayHello());
  }

}