package edu.psu.ist440.USAmeriCoin.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * RestApiService Object
 * Responsible for all the low-level HTTP REST API interactions
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Service
public class RestApiService {

    private String CoinMarketCapApi (String apiUrl) {
        int httpStatusCode;
        String apiResult = "";
        String acceptHeader = "application/json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", acceptHeader)
                .header("X-CMC_PRO_API_KEY","4367511e-72ab-43f9-8de7-8d46be6077fc")
                .uri(URI.create(apiUrl))
                .build();
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            httpStatusCode = response.statusCode();
            apiResult = response.body();
            return apiResult;
        } catch ( IOException | InterruptedException e) {
            return apiResult;
        }
    }
}
