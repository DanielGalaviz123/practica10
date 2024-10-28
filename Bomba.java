public class Bomba extends Elemento implements Destruible {
    private int radio;

    public Bomba(Escenario escenario, Posicion posicion, int radio) {
        super(escenario, posicion);
        this.radio = 1;
    }

    @Override
    public String destruir() {
        return "Bomba destruida";
    }

    public void explotar() {
        System.out.println("Explotando bomba!!");
        escenario.destruirElementos(posicion, radio);
    }
}

