/**
* @author  Basilico Enea, enea.basilico@gmail.com

* @file montagna.java 
* 
* @brief Contiene la classe montagna
*
*/

/**
 * @class montagna
 * 
 * @brief Rappresenta la mappa di gioco
 * @version 1.2
 * @since 1.0
 * @details Tramite una matrice di interi rappresenta la mappa di gioco
 */
public class montagna {

    /**
     * Matrice di interi di dimensioni definite in settings.java che rappresenta la
     * mappa di gioco
     */
    private int[][] montagne; // matrice 20x20 che funziona da heightMap
    /**
     * Array di tipo coord che contiene le coordinate delle settings.numeroCime cime
     */
    private coord[] listaVette; // array che contiene x e y delle tre vette

    /**
     * @brief Costruttore
     * @details Inizializza i due array montagne e listaVette
     */
    public montagna() {
        montagne = new int[settings.grandezzaMatrice][settings.grandezzaMatrice];
        listaVette = new coord[settings.numeroCime];
    }

    /**
     * @brief Ritorna la matrice montagne
     * 
     * @return int[][] montagne
     */
    public int[][] getMontagne() {
        return montagne;
    }

    /**
     * @brief Ritorna il valore della cella richiesta
     * 
     * @param x[in] della cella
     * @param y[in] della cella
     * @return -1 se x e/o y non sono validi;
     *         int montagne[x][y] se lo sono
     */
    public int getCella(coord pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (x >= 0 && x < settings.grandezzaMatrice && y >= 0 && y < settings.grandezzaMatrice)
            return montagne[y][x];
        return -1;
    }

    /**
     * @brief Ritorna la x della vetta numero numeroVetta
     * 
     * @param numeroVetta[in]
     * 
     * @return -1 se numeroVetta non è valido;
     *         int listaVette[numeroVetta].getX(); se lo sono
     */
    public int getXVetta(int numeroVetta) {
        if (numeroVetta >= 0 && numeroVetta < settings.numeroCime)
            return listaVette[numeroVetta].getX();

        return -1;
    }

    public coord getVetta(int numeroVetta){
        if(numeroVetta>=0 && numeroVetta < settings.numeroCime)
            return listaVette[numeroVetta];
        return new coord(-1,-1);
    }

    /**
     * @brief Ritorna la y della vetta numero numeroVetta
     * 
     * @return -1 se numeroVetta non è valido;
     *         int listaVette[numeroVetta].getY(); se lo sono
     */
    public int getYVetta(int numeroVetta) {
        if (numeroVetta >= 0 && numeroVetta < settings.numeroCime)
            return listaVette[numeroVetta].getY();
        return -1;
    }

    // metodo che riempe la matrice
    /**
     * @brief Riempe la matrice di valori rappresentanti l'altezza delle varie
     *        "caselle" della mappa
     * @details Prima chiama il metodo creaVette per generare le vette, poi tramite
     *          una variabile offSet si allontana sempre di più dalla vetta e
     *          aumenta il
     *          numero con il quale vengono riempite le celle finchè offSet non
     *          arriva al valore massimo,
     *          poi riempi eventuali buchi
     */
    public void fill() {

        creaVette();

        int offSet = 0;

        do {
            for (int i = 0; i < listaVette.length; i++) {
                // per ogni vertice "espandi" la vetta di 1 di raggio
                espandiVetta(listaVette[i], offSet);
            }
            offSet++;
        } while (offSet < settings.pianura);// finche non ha riempito tanti cerchi intorno alle vette quanto indicato
                                            // dal
        // livello della settings.pianura

        // riempi eventuali buchi
        for (int i = 0; i < settings.grandezzaMatrice; i++) {
            for (int j = 0; j < settings.grandezzaMatrice; j++) {
                if (montagne[i][j] == 0) {
                    montagne[i][j] = settings.pianura;
                }
            }
        }
    }

    /**
     * @brief Assegna valori x e y random a ogni vetta
     * 
     * @details Assegna valori randomici alle vette controllando che non si
     *          sovrapponino
     */
    private void creaVette() {
        for (int i = 0; i < listaVette.length; i++) {
            boolean ripetuto = false;
            int x = (int) (Math.random() * settings.grandezzaMatrice),
                    y = (int) (Math.random() * settings.grandezzaMatrice);
            do {
                if (i > 0)
                    if (x == listaVette[i - 1].getX() && y == listaVette[i - 1].getY())
                        ripetuto = true;
            } while (ripetuto);
            listaVette[i] = new coord(x, y);
        }
    }

    //
    /**
     * 
     * @param x[in]
     * @param y[in]
     * @param offSet[in]
     * @brief Ricevute le cordinate del vertice e l'offset, spostati dalle x e y
     *        ricevute di quanto dice l'offSet e
     *        riempi la cerchia corrispondente del valore offSet+1
     */
    private void espandiVetta(coord p, int offSet) {
        //punto altosx, altodx, bassosx, bassodx
        coord aSx = new coord(p.getX()-offSet, p.getY()-offSet), aDx = new coord(p.getX()+offSet, p.getY()-offSet);
        coord bSx = new coord(p.getX()-offSet, p.getY()+offSet), bDx = new coord(p.getX()+offSet, p.getY()+offSet); 

        // linea superiore
        riempiLinea(aSx, aDx, offSet + 1);
        // linea inferiore
        riempiLinea(bSx, bDx, offSet + 1);
        // linea sinistra
        riempiLinea(aSx, bSx, offSet + 1);
        // linea destra
        riempiLinea(aDx, bDx, offSet + 1);
    }

    /**
     * @brief Riempe con un dato numero una linea dati due punti
     * @details Riempe una linea strettamente orizzontale o verticale appartenente
     *          alla matrice con il numero passato come parametro
     * @param x1[in]
     * @param y1[in]
     * @param x2[in]
     * @param y2[in]
     * @param numero[in]
     */
    private void riempiLinea(coord p1, coord p2, int numero) {
        // se le ordinate sono uguali, la linea sarà orizzontale, altrimenti verticale
        int x1 = p1.getX(), x2 = p2.getX();
        int y1 = p1.getY(), y2 = p2.getY();
        if (y1 == y2) {
            int lowerBound = Math.min(x1, x2); // estremo di partenza del segmento
            int upperBound = Math.max(x1, x2); // estremo di arrivo
            for (int i = lowerBound; i <= upperBound; i++)
                if (i >= 0 && i < settings.grandezzaMatrice && y1 >= 0 && y1 < settings.grandezzaMatrice)
                    if (montagne[y1][i] == 0)
                        montagne[y1][i] = numero;
        } else if (x1 == x2) {
            int lowerBound = Math.min(y1, y2);
            int upperBound = Math.max(y1, y2);
            for (int i = lowerBound; i <= upperBound; i++)
                if (i >= 0 && i < settings.grandezzaMatrice && x1 >= 0 && x1 < settings.grandezzaMatrice)
                    if (montagne[i][x1] == 0)
                        montagne[i][x1] = numero;
        }
    }

    /**
     * @brief Converte da matrice di interi a stringa la montagna
     * @return string
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < settings.grandezzaMatrice; i++) {
            for (int j = 0; j < settings.grandezzaMatrice; j++)
                if (montagne[i][j] != 0)
                    s += montagne[i][j] + " ";
                else
                    s += "  ";
            s += "\r\n";
        }
        return s;
    }

    // ricevute le coordinate del player mette al suo posto il carattere designato
    // al posto del valore della cella
    /**
     * @brief Converte da matrice di interi a stringa la montagna, sostituendo alla
     *        cella corrispondente alle cordinate passate come parametro
     *        un carattere speciale per indicare il player
     * @param xP
     * @param yP
     * @return String
     */
    public String toString(coord coordP) {
        String s = "";
        int x = coordP.getX(), y = coordP.getY();
        for (int i = 0; i < settings.grandezzaMatrice; i++) {
            for (int j = 0; j < settings.grandezzaMatrice; j++)
                if (i == y && j == x)
                    s += "+ ";
                else if (montagne[i][j] != 0)
                    s += montagne[i][j] + " ";
                else
                    s += "  ";

            s += "\r\n";
        }
        return s;
    }
}