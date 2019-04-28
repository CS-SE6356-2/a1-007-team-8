package gofish.controllers;

import gofish.models.Game;

public class Rule {

    public interface RuleChecker {
        /**
         * Check if rule is violated or not
         * @param game current game state
         * @param value the value to test the game state against
         * @return true if rule is not violated, false otherwise
         */
        boolean isMet(Game game, int value);
    }

    public interface ValueCalculator {
        /**
         * Calculate the value for the rule based on the game state
         * @param game The current game state
         * @return The calculated value based on the game state
         */
        int calulateValue(Game game);
    }

    private int value;
    private String name;
    private RuleChecker rc;

    private ValueCalculator vc;

    public Rule(String name, int value, RuleChecker rc) {
        this.name = name;
        this.value = value;
        this.rc = rc;
    }

    public Rule(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Rule(String name, ValueCalculator vc) {
        this.name = name;
        this.vc = vc;
    }

    public Rule(String name, ValueCalculator vc, RuleChecker rc) {
        this(name, vc);
        this.rc = rc;
    }

    /**
     * Get the static value
     * @return The static value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the dynamic value based on the game state
     * @param g The game state
     * @return value calculated by the ValueCalculator
     */
    public int getValue(Game g) {
        return vc.calulateValue(g);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleChecker getRuleChecker() {
        return rc;
    }

    public void setRuleChecker(RuleChecker rc) {
        this.rc = rc;
    }

    public ValueCalculator getValueCalculator() {
        return vc;
    }

    public void setValueCalculator(ValueCalculator vc) {
        this.vc = vc;
    }

    /**
     * Check if the rule passes
     * @param g The current game state
     * @return true if rule passes, false if not
     */
    public boolean isMet(Game g) {
        if (rc == null) {
            return true;
        } else {
            if (vc == null) {
                return rc.isMet(g, value);
            } else {
                return rc.isMet(g, vc.calulateValue(g));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rule) {
            return ((Rule)o).getName().equals(this.name) && ((Rule)o).getValue() == this.value;
        } else if (o instanceof String) {
            return o.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " " + getValue();
    }
}
