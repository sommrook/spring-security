package com.cos.security1.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class SecurityConfigTest {

    @Getter
    static class ChaningPeople {
        private int height;
        private int weight;
        private String residence;

        public ChaningPeople setHeight(int height) {
            this.height = height;
            return this;
        }
        public ChaningPeople setWeight(int weight) {
            this.weight = weight;
            return this;
        }
        public ChaningPeople setResidence(String residence) {
            this.residence = residence;
            return this;
        }
    }


    @Test
    @DisplayName("method chaning")
    public void methodChaning(){
        ChaningPeople chaningPeople = new ChaningPeople();
        chaningPeople.setHeight(13)
                .setWeight(15)
                .setResidence("test");
        System.out.println("chaningPeople = " + chaningPeople);
        System.out.println("chaningPeople.getResidence() = " + chaningPeople.getResidence());
        System.out.println("chaningPeople.getWeight() = " + chaningPeople.getWeight());
        System.out.println("chaningPeople.getHeight() = " + chaningPeople.getHeight());
    }

}