package gofish.controllers;

import gofish.models.Game;

public class Rule {

    interface RuleChecker {
        boolean isMet(Game game, int value);
    }

    interface ValueCalculator {
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

    public int getValue() {
        return value;
    }

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
