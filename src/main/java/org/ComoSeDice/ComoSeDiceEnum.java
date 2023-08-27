package org.ComoSeDice;

/** Enum class to store all english to spanish words. */
public enum ComoSeDiceEnum {
  Apple("Manzana"),
  Grape("Uva"),
  Watermelon("Sandia"),
  Strawberry("Fresa"),
  Guava("Guayaba"),
  Soccer("Futbol"),
  Stapler("Grapadora"),
  Mountain("Montana"),
  Floor("Piso"),
  Television("Television"),
  Computer("Computadora"),
  Fan("Ventilador");

  private final String spanish;

  ComoSeDiceEnum(String spanish) {
    this.spanish = spanish;
  }

  /**
   * Gets a random value from the English side of the enum.
   *
   * @return English word
   */
  public static ComoSeDiceEnum getRandom() {
    return values()[(int) (Math.random() * values().length)];
  }

  /**
   * Gets the Spanish value of the English word.
   *
   * @return Spanish word
   */
  public String getSpanish() {
    return this.spanish;
  }
}
