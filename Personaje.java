public abstract class Personaje extends Elemento implements Destruible {
    protected String nombre;
    protected int puntosDeVida;

    public Personaje(String nombre, Escenario escenario, Posicion posicion) {
        super(escenario, posicion); // Llama al constructor de Elemento correctamente
        this.nombre = nombre; // Asigna el nombre
        this.puntosDeVida = 100; // Valor inicial de puntos de vida
    }

    public String getNombre() {
        return nombre;
    }
}
