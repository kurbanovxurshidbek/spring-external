package com.network.external;

import com.network.external.model.*;
import com.network.external.network.RestNetwork;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalApplication.class, args);

        // For Husanboy
        var resp1= RestNetwork.post(RestNetwork.API_ALIGO_SMS,RestNetwork.paramsAligo(new AligoBody()));
        System.out.println(resp1);

        // For Zuhriddin
        List<String> b_no = new ArrayList<>();
        b_no.add("0000000000");
        BusinessBody businessBody = new BusinessBody();
        businessBody.setB_no(b_no);

        var resp2= RestNetwork.post(RestNetwork.API_BUSINESS_NUMBER, businessBody,RestNetwork.paramsBusiness());
        System.out.println(resp2);

        // For Sevinch
        TokenBody tokenBody = new TokenBody();
        var resp3= RestNetwork.post(RestNetwork.API_AUTH_TOKEN, tokenBody);
        System.out.println(resp3);

        KeygenBody keygenBody = new KeygenBody();
        var resp4 = RestNetwork.post(RestNetwork.API_BILL_KEYGEN, keygenBody, RestNetwork.headersKeygen());
        System.out.println(resp4);
    }

}
