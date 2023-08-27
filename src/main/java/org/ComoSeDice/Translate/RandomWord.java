package org.ComoSeDice.Translate;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

/** This class is to call the api - RANDOM_WORD_URL - using WebClient to get a random word. */
@Component
public class RandomWord {

  /** Web client used to call Random word api. */
  private final WebClient randomWordWebClient;

  /** Web client used to call translate word api. */
  private final WebClient translateWordClient;

  public RandomWord(
      @Qualifier(WebClients.BEAN_NAME_RANDOM_WORD) WebClient randomWordWebClient,
      @Qualifier(WebClients.BEAN_NAME_TRANSLATE_WORD) WebClient translateWordClient) {
    this.randomWordWebClient = randomWordWebClient;
    this.translateWordClient = translateWordClient;
  }
  /**
   * This method will call the api in RANDOM_WORD_URL to get a random word to use.
   *
   * @return random word
   */
  public String getRandomWordWithWebClient() {
    ResponseSpec responseSpec = randomWordWebClient.get().retrieve();
    String response = responseSpec.bodyToMono(String.class).block();
    Object obj = JSONValue.parse(response);

    JSONObject jsonObject = (JSONObject) obj;

    return (String) jsonObject.get("word");
  }

  /**
   * This method will call the api in TRANSLATE_WORD_URL to get the translated word.
   *
   * @param wordToTranslate the word that will be translated to spanish
   */
  public void getTranslatedWordWithWebClient(String wordToTranslate) {
  }
}
