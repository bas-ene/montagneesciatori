/**
* @author  Basilico Enea, enea.basilico@gmail.com
* @file player.java 
* 
* @brief Contiene la classe player
*
*/

/**
 * @class player
 * @version 1.0
 * @since 1.0
 * @brief Classe che simboleggia il player, contendo le sue coordinate e la sua
 *        velocità
 * 
 * @details Simboleggia il player, contendo le sue coordinate e la sua velocità
 *          e i metodi per farlo muovere
 */
public class player {
    /** posizione del player */
    private coord pos;
    /** velocità del player */
    private float vel;

    /** @brief Costruttore, imposta tutto a 0 */
    public player() {
        pos = new coord(0, 0);
        vel = 0;
    }

    /**
     * @brief Costruttore parametrico per impostare le coordinate di base del player
     * @param x[in] del player
     * @param y[in] del player
     */
    public player(int x, int y) {
        this.pos.setXY(x, y);
        vel = 0.0f;
    }

    /**
     * @brief Muove il player in base al parametro direction [WASD]
     * @param direction[in]
     */
    public void move(char direction) {
        direction = Character.toLowerCase(direction);
        switch (direction) {
            case 'a': // sinistra
                pos.setX(pos.getX() - 1);
                break;
            case 'd': // destra
                pos.setX(pos.getX() + 1);
                break;
            case 's': // giù
                pos.setY(pos.getY() + 1);
                break;
            case 'w': // su
                pos.setY(pos.getY() - 1);
                break;
        }
    }

    
    /**
     * 
     * @return vel del player
     */
    public float getVel() {
        return vel;
    }

    /**
     * Aumenta o diminuisce la velocità del player
     * 
     * @param delta[in]
     */
    public void setVel(float delta) {
        this.vel += delta;
    }

    /**
     * Getter della ppsizione del player
     * @return pos
     */
    public coord getPos() {
        return pos;
    }

    /**
     * Setter della posizione del player
     * @param pos
     */
    public void setPos(coord pos) {
        this.pos = pos;
    }

}