package fontys.sem3.group.sioux.logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import fontys.sem3.group.sioux.model.SMS;

public class TextSender {
    public static final String ACCOUNT_SID = "AC2335f53b5b892b23cdce824c91f88c22";
    public static final String AUTH_TOKEN = "7e5900055a065b52613c85e0c3ab8fdb";

    public void sentText(SMS sms){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getReceiver()),
                new PhoneNumber(sms.getSender()),
                sms.getMessage()).create();

        message.getSid();
    }
}
