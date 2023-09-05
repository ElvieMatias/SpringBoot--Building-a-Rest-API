package com.edm.cashcards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/*
     The @JsonTest annotation marks the CashCardJsonTest as a test class which uses the
     Jackson framework (which is included as part of Spring).
     This provides extensive JSON testing and parsing support.
     It also establishes all the related behavior to test JSON objects
*/
@JsonTest
public class CashCardJsonTest {
//    @Autowired is an annotation that directs Spring to create an object of the requested type
    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    public void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(77L, 777.00);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(77);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(777.00);
    }

    @Test
    public void cashCardDeserializationTest() throws IOException{
        String expected = """
               {
                "id": 77,
                "amount" : 777.00
               }
               """;
        assertThat(json.parse(expected)).isEqualTo(new CashCard(77L, 777.00));
        assertThat(json.parseObject(expected).id()).isEqualTo(77);
        assertThat(json.parseObject(expected).amount()).isEqualTo(777.00);
    }

}
