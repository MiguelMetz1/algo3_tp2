package edu.fiuba.algo3.classesTests;

import edu.fiuba.algo3.Model.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    @Test
    public void messageGreeting() {
        Message message = new Message("Hola Mundo!", "Hello world!");

        assertEquals("Hello world!", message.greet("us"));
    }

    @Test
    public void messageGreetingDefaultLanguage() {
        Message message = new Message("Hola Mundo!", "Hello world!");

        assertEquals("Hola Mundo!", message.greet());
    }
}
