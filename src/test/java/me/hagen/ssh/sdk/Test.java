package me.hagen.ssh.sdk;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Íõ¾µöÎ on 17-3-29 ÏÂÎç7:16.
 */
public class Test {
    public static void main(String[] args) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        String ACCOUNT_SID = "AC188501fa11bc0048f269df6688c2812f";
        String AUTH_TOKEN = "158add5fb66ff4467ccbb556674713a5";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("(647) 978-1466"),new PhoneNumber("(647) 799-6688"),"666").create();
   /*     RequestOptions requestOptions = RequestOptions.builder().setApiKey("sk_test_PzIAQZE3Hr7lqvkKAS4jpEvh").build();
        Map<String,Object> chargeMap = new HashMap();
        chargeMap.put("amount",100);
        chargeMap.put("currency","usd");
        Map<String,Object> cardMap = new HashMap<>();
        cardMap.put("number", "4242424242424242");
        cardMap.put("exp_month", 12);
        cardMap.put("exp_year", 2020);
        chargeMap.put("card", cardMap);
        Charge charge = Charge.create(chargeMap, requestOptions);*/
    }
}
