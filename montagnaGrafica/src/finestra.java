import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
* @author  Basilico Enea, enea.basilico@gmail.com
* @file finestra.java 
* 
* @brief Contiene la classe finestra, che si occupa della grafica
*
*/

/**
 * @class finestra
 * @version 1.2
 * @since 1.2
 * @brief Si occupa della parte grafica ereditando da JFrame
 * 
 * @details Si occupa di creare, inzializzare, disegnare e terminare la
 *          finestra, utilizzando la business logic della classe partita
 */

public class finestra extends JFrame implements KeyListener, MouseListener {
    /** oggetto di tipo partita da utilizzare per la logica del gioco */
    private partita p;
    /** Array che contiene i colori per disegnare le montagne */
    private Color[] palette;
    /** Array che contiene i colori possibili del player */
    private Color[] coloriGiocatore;
    /**
     * Parametro in cui viene memorizzata la coordinata corrispondente del Player
     * nella finestra
     */
    private coord cP;
    /** Seleziona il colore del player nella finestra */
    private int inidiceColore;

    /**
     * @brief Costruttore
     * @details Si occupa di inizializzare il frame tramite initFrame() e di far
     *          inziare la partita
     */
    public finestra() {
        super("Sciatori e montagne");
        p = new partita();
        p.start();
        initFrame();
        inidiceColore = 0;
        setPalette();
        cP = new coord(p.getP1().getPos());
    }

    /**
     * @brief Si occupa di inizializzare il frame
     * @details Raggrupa in un unico metodo tutti i metodi per le impostazioni della
     *          finestra, come dimensione e aggiunta di KeyListener e MouseListener
     */
    private void initFrame() {
        setPreferredSize(new Dimension(settings.widthFinestra, settings.heigthFinestra));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        requestFocus();
    }

    /**
     * @brief Imposta i colori da utilizzare per le montagne e il giocatore
     */
    private void setPalette() {
        palette = new Color[settings.pianura];
        coloriGiocatore = new Color[settings.numeroColori];
        for (int i = 0; i < palette.length; i++)
            palette[i] = new Color(0, 20 * i, 0);

        coloriGiocatore[0] = new Color(255, 0, 0);
        coloriGiocatore[1] = new Color(0, 255, 0);
        coloriGiocatore[2] = new Color(0, 0, 255);
        palette[0] = new Color(139, 69, 19);

    }

    private Image offScreenImageDrawed = null;
    private Graphics offScreenGraphicsDrawed = null;

    /**
     * @brief Override del metodo paint, sfruttando un double buffer
     */
    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        final Dimension d = getSize();
        if (offScreenImageDrawed == null) {
            // Double-buffer: clear the offscreen image.
            offScreenImageDrawed = createImage(d.width, d.height);
        }
        g.clearRect(0, 0, this.getWidth(), this.getHeight()); // super.paint(g);
        offScreenGraphicsDrawed = offScreenImageDrawed.getGraphics();
        offScreenGraphicsDrawed.setColor(Color.white);
        offScreenGraphicsDrawed.fillRect(0, 0, d.width, d.height);
        renderOffScreen(offScreenImageDrawed.getGraphics());
        g.drawImage(offScreenImageDrawed, 0, 0, null);

    }

    /**
     * @brief Metodo per effetivamente disegnare il frame
     * @details Disegna il frame con quadrati di dimensioni predefinite in base al
     *          valore della cella corrispondente della montagna, o in caso del
     *          player in base alle sue coordinate, oltre che a chiamare il metodo
     *          per mostrare la velocità
     */
    public void renderOffScreen(final Graphics g) {
        int[][] m = p.getMont().getMontagne();
        for (int i = 0; i < settings.grandezzaMatrice; i++) {
            for (int j = 0; j < settings.grandezzaMatrice; j++) {
                if (j == p.getP1().getPos().getX() && i == p.getP1().getPos().getY()) {
                    g.setColor(coloriGiocatore[inidiceColore]);
                    cP.setXY(j * settings.latoQuadrato,i * settings.latoQuadrato + settings.heightBarraTitolo);
                } else
                    g.setColor(palette[(m[i][j]) - 1]);
                g.fillRect(j * settings.latoQuadrato, i * settings.latoQuadrato + settings.heightBarraTitolo,
                        settings.latoQuadrato, settings.latoQuadrato);
            }
        }
        visualizzaVel(g);
    }

    /**
     * @brief Mostra in alto a sinistra la velocità del giocatore
     * @details Disegna tramite un oggetto Graphics2D ottenuto castando il parametro
     *          di classe Graphics. Il colore del testo corrisponde a quello del
     *          giocatore
     * @param g[in]
     */
    public void visualizzaVel(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setColor(coloriGiocatore[inidiceColore]);
        g2.setFont(font);
        String vel = "";
        vel = Float.toString(p.getP1().getVel());
        g2.drawString(vel, font.getSize() / 2, font.getSize() + settings.heightBarraTitolo);
    }

    /**
     * @brief Mostra una MessageDialog che ti comunica la sconfitta
     */
    private void displayLose() {
        JOptionPane.showMessageDialog(this, "Hai perso", "L", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @brief Mostra una MessageDialog che ti comunica la sconfitta
     */
    private void displayWin() {
        JOptionPane.showMessageDialog(this, "Hai vinto", "W", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * @brief Muove il player
     * @details Muove il player nella direzione indicata [WASD] se la partita è
     *          ancora in corso,
     *          altrimenti termina il gioco mostrando o la finestra della vittoria o
     *          della sconfitta
     */
    @Override
    public void keyPressed(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        if ((p.checkWinOrLose() == 0))
            p.round(c);
        repaint();

        if (p.checkWinOrLose() == 1) {
            displayWin();
            dispose();
        } else if (p.checkWinOrLose() == -1) {
            displayLose();
            dispose();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * @brief Cambia colore al player
     * @details Registrato il click del mouse controlla le x e y del click, se sono
     *          interne al quadrato rappresentante il player nella finestra modifica
     *          l'indice
     *          utilizzato dal metodo paint sull'array dei colori possibili del
     *          player
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // controllo se è fuori, se no allora cambia colore
        // più a sinistra del punto più a sinistra o destra del punto più a destra, più
        // in basso del punto più in basso ecc
        if (!(x < cP.getX() || x > cP.getX() + settings.latoQuadrato || y < cP.getY() || y > cP.getY() + settings.latoQuadrato)) {
            inidiceColore = (inidiceColore + 1) % coloriGiocatore.length;
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
