/**
* @author  Basilico Enea, enea.basilico@gmail.com

* @file coord.java 
* 
* @brief Contiene la classe coord
*
*/

/**
 * @class coord
 * @version 1.0
 * @since 1.1
 * @brief Classe che contiene gli attributi x e y, in modo da raggrupare in un
 *        unica classe entrambe le coordinate
 * 
 * @details Assume il ruolo di raggruppare in un unico oggetto le variabile
 *          utili
 *          al player o alla map, rendendo più chiaro il codice
 */

public class coord {

    /** Ascissa */

    private int x;
    /** Ordinata */
    private int y;

    /**
     * @brief Costruttore date x e y
     * 
     * @details Assegna agli attributi i valori passati come parametri
     * @param x[in] Ascisse
     * @param y[in] Ordinata
     */
    public coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @brief Costruttore date x e y
     * 
     * @details Assegna agli attributi i valori passati come parametri
     * @param x[in] Ascisse
     * @param y[in] Ordinata
     */
    public coord(coord punto) {
        this.x = punto.getX();
        this.y = punto.getY();
    }

    /**
     * @brief Imposta l'attributo x
     *
     * @param x[in] Valore da assegnare
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @brief Imposta l'attributo y
     *
     * @param y[in] Valore da assegnare
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @brief Imposta sia x che y
     * @details Chiama setX(int x) e setY(int y)
     *
     * @param x[in] Valore da assegnare alle ascisse
     * @param y[in] Valore da assegnare alle ordinate
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @brief Ritorna x
     * @return x ascisse
     */
    public int getX() {
        return x;
    }

    /**
     * @brief Ritorna y
     * @return y ordinate
     */
    public int getY() {
        return y;
    }

}
