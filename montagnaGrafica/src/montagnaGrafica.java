/**
 * @mainpage Montagne e sciatori in versione grafica
 * @section intro_sec Introduzione al progetto
 * Il progetto Montagne e sciatori è un gioco nel quale si impersona uno sciatore che, partendo da una delle numerose cime, deve arrivare a valle senza perdere troppa velocità.
 * @section evolution_sec Evoluzione del progetto
 * Il progetto è iniziato come un'applicazione in console, con la mappa rappresentata dai suoi valori numerici e il giocatore da un carattere speciali. <br>
 * Poi si è evoluta spostandosi nell'ambiente grafico e aggiungendo funzionalità come l'indicazione della velocità, la possibilità di cambiare colore al giocatore.
 * @section command_sec Comandi del gioco
 * Ci si muove con WASD e clickando col mouse sul giocatore si può cambiare il colore del player.
 * @note 
 * @ref struct_page<br>
 * @ref changelog_page
 */

/**
 * @page struct_page Struttura del progetto
 * @section struct_sec Strutturazione del progetto
 * Il progetto è strutturato come segue:  
 * 
 */

/**
 * @page changelog_page Changelog
 * @section struct_sec 1.2
 * Il progetto è strutturato come segue:  
 * 
 */

/**
 * @author Basilico Enea, enea.basilico@gmail.com
 * 
 * @file montagnaGrafica.java
 * 
 * @brief Main
 *
 */
/**
 * @class montagnaGrafica
 * @version 1.2
 * @since 1.0
 * @brief Main
 * @details Inizializza la finestra
 */
public class montagnaGrafica {
    public static void main(String[] args) {
        finestra f = new finestra();
        f.paint(f.getGraphics());
    }
}