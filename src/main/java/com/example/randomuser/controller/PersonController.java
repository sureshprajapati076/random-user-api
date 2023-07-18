package com.example.randomuser.controller;

import com.example.randomuser.model.User;
import com.example.randomuser.repo.PersonRepo;
import com.jayway.jsonpath.JsonPath;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private final PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @PostMapping("/post")
    public String postAll(@RequestBody String results) {

        var parsedRequest = JsonPath.parse(results);

        int index = parsedRequest.read("$.info.results");

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            try {
                var user = User.builder()
                        .dob(parsedRequest.read("$.results[" + i + "].dob.date"))
                        .phone(parsedRequest.read("$.results[" + i + "].phone"))
                        .age(parsedRequest.read("$.results[" + i + "].dob.age"))
                        .address(parsedRequest.read("$.results[" + i + "].location.street.name"))
                        .city(parsedRequest.read("$.results[" + i + "].location.city"))
                        .fname(parsedRequest.read("$.results[" + i + "].name.first"))
                        .lname(parsedRequest.read("$.results[" + i + "].name.last"))
                        .state(parsedRequest.read("$.results[" + i + "].location.state"))
                        .title(parsedRequest.read("$.results[" + i + "].name.title"))
                        .email(parsedRequest.read("$.results[" + i + "].email"))
                        .country(parsedRequest.read("$.results[" + i + "].location.country"))
                        .postalCode(parsedRequest.read("$.results[" + i + "].location.postcode").toString())
                        .gender(parsedRequest.read("$.results[" + i + "].gender"))
                        .build();
                userList.add(user);
            } catch (Exception ignored) {

            }

        }

        personRepo.saveAll(userList);

        return "DONE";
    }
}
