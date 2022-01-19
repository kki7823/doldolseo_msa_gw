package com.doldolseo.doldolseo_msa_gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class DoldolseoMsaGwApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoldolseoMsaGwApplication.class, args);
        System.out.println("패턴 테스트 : "+patternMatcher("localhost:8092/doldolseo/member"));
    }

    public static boolean patternMatcher(String uri) {
        Pattern pattern = Pattern.compile("(.*)(/doldolseo/member)$");
        return pattern.matcher(uri).find();
    }

}
