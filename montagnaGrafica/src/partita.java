/**
* @author  Basilico Enea, enea.basilico@gmail.com

* @file partita.java 
* 
* @brief Contiene la classe partita
*
*/

/**
 * @class partita
 * 
 * @brief Si occupa della logica di gioco
 * @version 1.1.1
 * @since 1.1
 * @details Si occupa di tutta la logica di gioco, raggrupando in un'unica
 *          classe che si interfaccia con la finestra player e montagna
 */

public class partita {

    /** Mappa di gioco */
    private montagna mont;
    /** Player */
    private player p1;

    /** Valore della cella precedente per calcolare il delta velocità */
    private int valCellaPrec;

    /** Costruttore che inizializza player e montagna */
    public partita() {
        mont = new montagna();
        p1 = new player();
        valCellaPrec = 0;
    }

    /** Richiama i metodi per riempire la mappa e posiziona il player su una veta */
    public void start() {
        // genera la mappa
        mont.fill();

        // scegli un numero compreso tra 0 e il numero massimo di vette possibili,
        // scegli la vetta corrispondente a quel numeroo e assegna al player le
        // coordinate di quella vetta
        int vetta = (int) (Math.random() * settings.numeroCime); // seed.nextInt(settings.numeroCime);
        // p1.setPos(mont.getXVetta(vetta), mont.getYVetta(vetta));
        p1.setPos(mont.getVetta(vetta));
        valCellaPrec = mont.getCella(p1.getPos());
    }

    /**
     * Data una direzione tramite il parametro, se è una mossa legale muove il
     * player nella direzione e modifica la sua velocità
     * 
     * @param direction[in]
     */
    public void round(char direction) {
        coord cPrec = p1.getPos();
        if (direction == 'a' || direction == 's' || direction == 'w' || direction == 'd') {
            p1.move(direction);

           coord cCorr = p1.getPos();

            if (cCorr.getX() < 0 || cCorr.getX() >= settings.grandezzaMatrice
                    || cCorr.getY() < 0 || cCorr.getY() >= settings.grandezzaMatrice) { // se il player esce dalla mappa
                p1.setPos(cPrec); // annulla la mossa
            } else {
                float delta = 0;
                int valCellaCorr = mont.getCella(cCorr);
                if (valCellaCorr > valCellaPrec)
                    delta = settings.deltaDiscesa;
                else if (valCellaCorr == valCellaPrec)
                    delta = settings.deltaPianura;
                else
                    delta = settings.deltaSalita;
                p1.setVel(delta);
                valCellaPrec = valCellaCorr;
            }
        }
    }

    /**
     * Ritorna un valore in base alla situazione della partita
     * 
     * @return -1 se la partita è persa, 0 se è ancora in corso e 1 se è vinta
     */
    public int checkWinOrLose() {
        int risPartita = 0;

        if (p1.getVel() < 0)
            risPartita = -1;
        if (mont.getCella(p1.getPos()) == settings.pianura)
            risPartita = 1;

        return risPartita;
    }

    /**Richiama il toSrting() della classe montagna */
    @Override
    public String toString() {
        return mont.toString(p1.getPos());
    }
    /**
     * 
     * @return montagna mont
     */
    public montagna getMont() {
        return mont;
    }
    /**
     * 
     * @return player p1
     */
    public player getP1() {
        return p1;
    }

}
