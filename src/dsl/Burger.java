package dsl;

import static dsl.Burger.Patty.*;
import static dsl.Burger.Topping.*;
import static dsl.Burger.VegPatty.*;
import static dsl.Burger.VegTopping.*;

public class Burger {

    private final Patty patty;
    private final Topping topping;

    private Burger(Patty patty, Topping topping) {
        this.patty = patty;
        this.topping = topping;
    }

    @Override
    public String toString() {
        return String.format("Burger[patty=%s,topping=%s]", this.patty.toString(), this.topping.toString());
    }

    private static BurgerBuilder burger() {
        return patty -> topping -> new Burger(patty, topping);
    }

    interface BurgerBuilder {
        ToppingBuilder with(Patty patty);

        default VegBurgerBuilder vegtarian() {
            return patty -> topping -> new Burger(patty, topping);
        }
    }

    interface VegBurgerBuilder {
        VegToppingBuilder with(VegPatty patty);
    }

    interface ToppingBuilder {
        Burger and(Topping topping);
    }

    interface VegToppingBuilder {
        Burger and(VegTopping topping);
    }

    public static class Patty {
        protected final String name;

        protected Patty(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Patty[name=%s]", this.name);
        }

        public static Patty beef() {
            return new Patty("beef");
        }

    }

    public static class VegPatty extends Patty {
        public VegPatty(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return String.format("VegPatty[name=%s]", this.name);
        }

        public static VegPatty mushroom() {
            return new VegPatty("mushroom");
        }
    }

    public static class Topping {
        protected final String name;

        protected Topping(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Topping[name=%s]", this.name);
        }


        public static Topping bacon() {
            return new Topping("bacon");
        }

    }

    public static class VegTopping extends Topping {

        protected VegTopping(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return String.format("VegTopping[name=%s]", this.name);
        }

        public static VegTopping cheese() {
            return new VegTopping("cheese");
        }
    }

    public static void main(String[] args) {
        Burger b = burger()
                .with(beef())
                .and(bacon());
        System.out.println(b);
        Burger vb = burger()
                .vegtarian()
                .with(mushroom())
                .and(cheese());
        System.out.println(vb);
    }
}
