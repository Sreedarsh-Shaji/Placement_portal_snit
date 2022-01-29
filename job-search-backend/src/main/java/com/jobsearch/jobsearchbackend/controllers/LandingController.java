package com.jobsearch.jobsearchbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

    String documentDetails = "<center>" +
            "<h2><u> - JOB PORTAL : Backend information document - </u></h2>" +
            "<table>" +
            "<tr>" +
            "<td colspan='2'>" +
            "This is the backend application mainly written using - Java Spring boot" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<th>Swagger URL : </th>" +
            "<td> <a href = 'http://localhost:8080/get-swagger-documentation.html'>Swagger UI</a> </td>" +
            "<tr>" +
            "</table>" +
            "</center>";

    @GetMapping("/")
    @Operation( summary = "Gives info regarding the application")
    public String infoPage()
    {
        return documentDetails;
    }

}
