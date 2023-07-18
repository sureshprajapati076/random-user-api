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
public class UserController {

    private static final String RESULTS = "$.results[INDEX]";
    private final PersonRepo personRepo;

    public UserController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @PostMapping("/post")
    public String postAll(@RequestBody String results) {

        var parsedRequest = JsonPath.parse(results);

        int index = parsedRequest.read("$.info.results");

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            try {
                var prefixJsonPath = RESULTS.replace("INDEX",String.valueOf(i));
                var user = User.builder()
                        .dob(parsedRequest.read(prefixJsonPath+"dob.date"))
                        .phone(parsedRequest.read(prefixJsonPath+"phone"))
                        .age(parsedRequest.read(prefixJsonPath+"dob.age"))
                        .address(parsedRequest.read(prefixJsonPath+"location.street.name"))
                        .city(parsedRequest.read(prefixJsonPath+"location.city"))
                        .fname(parsedRequest.read(prefixJsonPath+"name.first"))
                        .lname(parsedRequest.read(prefixJsonPath+"name.last"))
                        .state(parsedRequest.read(prefixJsonPath+"location.state"))
                        .title(parsedRequest.read(prefixJsonPath+"name.title"))
                        .email(parsedRequest.read(prefixJsonPath+"email"))
                        .country(parsedRequest.read(prefixJsonPath+"location.country"))
                        .postalCode(parsedRequest.read(prefixJsonPath+"location.postcode").toString())
                        .gender(parsedRequest.read(prefixJsonPath+"gender"))
                        .build();
                userList.add(user);
            } catch (Exception ignored) {

            }

        }

        personRepo.saveAll(userList);

        return "DONE";
    }
}
