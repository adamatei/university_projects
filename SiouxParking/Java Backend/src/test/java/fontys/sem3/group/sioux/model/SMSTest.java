package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SMSTest {

    //constructor
    @Test
    void smsConstructorTest(){

        //ARRANGE section
        SMS sms = new SMS("Sioux Company", "Visitor's Name", "Park on the first spot from the main parking lot");

        //ACT section
        String expectedSender = "Sioux Company";
        String expectedReceiver = "Visitor's Name";
        String expectedMessage = "Park on the first spot from the main parking lot";

        //ASSERT section
        assertEquals(expectedSender,sms.getSender());
        assertEquals(expectedReceiver,sms.getReceiver());
        assertEquals(expectedMessage,sms.getMessage());
    }

    //setter and getter for 'sender'
    @Test
    void senderSetterGetterTest(){

        //ARRANGE section
        SMS sms = new SMS("Sioux Company", "Visitor's Name", "Park on the first spot from the main parking lot");

        //ACT section
        sms.setSender("Philips Company");
        String expectedSender = "Philips Company";

        //ASSERT section
        assertEquals(expectedSender,sms.getSender());
    }

    //setter and getter for 'receiver'
    @Test
    void receiverSetterGetterTest(){

        //ARRANGE section
        SMS sms = new SMS("Sioux Company", "Visitor's Name", "Park on the first spot from the main parking lot");

        //ACT section
        sms.setReceiver("New Visitor's Name");
        String expectedReceiver = "New Visitor's Name";

        //ASSERT section
        assertEquals(expectedReceiver,sms.getReceiver());
    }

    //setter and getter for 'message'
    @Test
    void messageSetterGetterTest(){

        //ARRANGE section
        SMS sms = new SMS("Sioux Company", "Visitor's Name", "Park on the first spot from the main parking lot");

        //ACT section
        sms.setMessage("Park on the second spot from the additional parking lot");
        String expectedMessage = "Park on the second spot from the additional parking lot";

        //ASSERT section
        assertEquals(expectedMessage,sms.getMessage());
    }
}
