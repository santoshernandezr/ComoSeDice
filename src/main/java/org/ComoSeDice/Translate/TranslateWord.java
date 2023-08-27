package org.ComoSeDice.Translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/** This class is to call the api - RANDOM_WORD_URL - using WebClient to get a random word. */
@Component
public class TranslateWord {

  /** Web client used to call translate word api. */
  private final WebClient translateWordClient;

  public TranslateWord(
      @Qualifier(WebClients.BEAN_NAME_TRANSLATE_WORD) WebClient translateWordClient) {
    this.translateWordClient = translateWordClient;
  }

  /**
   * This method will call the api in TRANSLATE_WORD_URL to get the translated word.
   *
   * @param wordToTranslate the word that will be translated to spanish
   */
  public void getTranslatedWordWithWebClient(String wordToTranslate) {}
}
