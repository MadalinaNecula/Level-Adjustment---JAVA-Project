public class EffectExecutor <T extends Effect>{ // clasa cu care rulez codul de executare efect - poate fi orice subclasa din effect

    private Effect effect; //T este tipul generic care trebuie sa mosteneasca Effect

    public EffectExecutor(T t) {//constructor

        this.effect = t;  // salvez instanta de effect de aplicat
    }

    public void aplicaEfect1() {

        effect.aplicaEfect(); // efect este de tip Effect si ii aplic metoda din Effect
        System.out.println("Efect salvat in imaginea: " +  effect.salveazaPoza() +" din folderul NewImages");
    }
}
