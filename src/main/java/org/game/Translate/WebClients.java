package org.game.Translate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/** Class that sets all the web clients that will be used in the application. */
@Configuration
public class WebClients {

  /**
   * Name of Spring bean for web client used by {@link RandomWord}
   *
   * <p>Value: <code>randomWordClient</code>
   */
  public static final String BEAN_NAME_RANDOM_WORD = "randomWordClient";

  /**
   * Name of Spring bean for web client used by {@link TranslateWord}
   *
   * <p>Value: <code>translateWordClient</code>
   */
  public static final String BEAN_NAME_TRANSLATE_WORD = "translateWordClient";

  /** Name of property that maps to Random word url. */
  private static final String PROPERTY_NAME_RANDOM_WORD_URL = "RANDOM_WORD_URL";

  /** Name of property that maps to Translate word url. */
  private static final String PROPERTY_NAME_TRANSLATE_WORD_URL = "TRANSLATE_WORD_URL";

  /** Name of property that maps to Random word api key. */
  private static final String PROPERTY_NAME_RANDOM_WORD_API_KEY = "RANDOM_WORD_API_KEY";

  /** Name of property that maps to Translate word api key. */
  private static final String PROPERTY_NAME_RANDOM_TRANSLATE_API_KEY = "TRANSLATE_WORD_API_KEY";

  /** Url of Random word api. */
  @Value("${" + PROPERTY_NAME_RANDOM_WORD_URL + "}")
  private String randomWordUrl;

  /** Url of Translate word api. */
  @Value("${" + PROPERTY_NAME_TRANSLATE_WORD_URL + "}")
  private String translateWordUrl;

  /** Api key for Random word api. */
  @Value("${" + PROPERTY_NAME_RANDOM_WORD_API_KEY + "}")
  private String randomWordApiKey;

  /** Api key for Translate word api. */
  @Value("${" + PROPERTY_NAME_RANDOM_TRANSLATE_API_KEY + "}")
  private String translateWordApiKey;

  /**
   * Web client used to call random word api.
   *
   * <p>Qualified by name {@link #BEAN_NAME_RANDOM_WORD}
   *
   * @return webClient
   */
  @Bean(name = BEAN_NAME_RANDOM_WORD)
  public WebClient getRandomWordWebClient() {
    return WebClient.builder()
        .baseUrl(randomWordUrl)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("X-Api-Key", randomWordApiKey)
        .build();
  }

  /**
   * Web client used to call translate word api. TODO - API to be determined
   *
   * <p>Qualified by name {@link #BEAN_NAME_TRANSLATE_WORD}
   *
   * @return webClient
   */
  @Bean(name = BEAN_NAME_TRANSLATE_WORD)
  public WebClient getTranslateWordWebClient() {
    return WebClient.builder().build();
  }
}
