/**
* @author  Basilico Enea, enea.basilico@gmail.com
* @file settings.java 
* 
* @brief Contiene la classe settings.java
*
*/

/**
 * @class settings
 * @version 1.0
 * @since 1.2
 * @brief Classe che contiene tutte le impostazioni da utilizzare nelle varie
 *        classi
 */

public class settings {
    /**
     * Lato dei quadrati disegnati dalla classe finestr.java per rappresentare
     * graficamente la mappa e il player
     */
    static public final int latoQuadrato = 40;
    /** Grandezza massima della matrice di interi della mappa */
    static public final int grandezzaMatrice = 20;
    /** Larghezza della finestra */
    static public final int widthFinestra = latoQuadrato * grandezzaMatrice;
    /** Altezza della barra del titolo della finestra */
    static public final int heightBarraTitolo = 31;
    /** Altezza della finestra */
    static public final int heigthFinestra = latoQuadrato * grandezzaMatrice + heightBarraTitolo;
    /** Numero massimo delle cime nella mappa */
    static public final int numeroCime = 3;
    /** Numero che rappresenta l'altezza più bassa della mappa */
    static public final int pianura = 9;
    /** Modificatore della velocità in discesa */
    static public final float deltaDiscesa = 1.0f;
    /** Modificatore della velocità in pianura */
    static public final float deltaPianura = -0.5f;
    /** Modificatore della velocità in salita */
    static public final float deltaSalita = -1.5f;
    /**Numero di colori possibili per il player */
    static public final int numeroColori = 3;
}
