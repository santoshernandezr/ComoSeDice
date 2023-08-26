package org.example.Translate;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

/** This class is to call the api - RANDOM_WORD_URL - using WebClient to get a random word. */
public class RandomWord {
  private static final String RANDOM_WORD_URL = "https://api.api-ninjas.com/v1/randomword";

  /**
   * This method will call the api in RANDOM_WORD_URL to get a random word to use.
   *
   * @return random word
   */
  public static String getRandomWordWithWebClient() {
    WebClient webClient = WebClient.create();

    ResponseSpec responseSpec =
        webClient
            .get()
            .uri(RANDOM_WORD_URL)
            .header("accept", MediaType.APPLICATION_JSON_VALUE)
            .header("X-Api-Key", "wALx18yAethCLquqwkU38g==ZbmVRxDiwyj5ALql")
            .retrieve();

    String response = responseSpec.bodyToMono(String.class).block();
    Object obj = JSONValue.parse(response);

    JSONObject jsonObject = (JSONObject) obj;

    return (String) jsonObject.get("word");
  }
}
