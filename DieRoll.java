
import java.util.Random;
import java.util.logging.Logger;

/**
 * Represents a die roll operation with specified number of dice, sides per die,
 * and a bonus value. Provides functionality to roll dice and get a result.
 */

public class DieRoll {

    private final int ndice;
    private final int nsides;
    private final int bonus;
    private static final Random rnd = new Random();
    private static final Logger logger = Logger.getLogger(DieRoll.class.getName());

    /**
     * Constructs a DieRoll instance with validation.
     *
     * @param ndice Number of dice to roll (must be > 0)
     * @param nsides Number of sides per die (must be > 0)
     * @param bonus Additional bonus to add to result
     * @throws IllegalArgumentException if ndice or nsides <= 0
     */
    public DieRoll(int ndice, int nsides, int bonus) {
        if (ndice <= 0 || nsides <= 0) {
            throw new IllegalArgumentException("Number of dice and sides must be > 0");
        }
        this.ndice = ndice;
        this.nsides = nsides;
        this.bonus = bonus;
    }

    /**
     * Rolls the dice and returns the result.
     *
     * @return RollResult object containing individual rolls and bonus
     */
    public RollResult makeRoll() {
        RollResult result = new RollResult(bonus);
        for (int i = 0; i < ndice; i++) {
            int roll = rnd.nextInt(nsides) + 1;
            result.addResult(roll);
            logger.info("Rolled: " +roll);
        }
        return result;
    }
    /**
     * Returns a string representation in format XdY±Z.
     * @return Description of the roll
     */

    @Override
    public String toString() {
        String description = ndice + "d" + nsides;
        if (bonus > 0) {
            description += "+" + bonus;
        } else if (bonus < 0) {
            description += bonus;
        }
        return description;
    }

}
