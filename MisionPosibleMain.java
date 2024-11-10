import java.io.*;
import java.util.*;

public class MisionPosibleMain {
    public static void main(String[] args) {
        Escenario e = new Escenario("Nostromo");

        // Leer la configuracion del archivo
        leerConfiguracion(e, "configuracion.txt");

        // Mostrar el estado inicial del escenario
        System.out.println("Estado inicial del escenario:");
        System.out.println(e);

        // Leer y ejecutar la detonacion de la bomba
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la posicion de la bomba para detonar (Renglon Columna): ");
        int renglon = scanner.nextInt();
        int columna = scanner.nextInt();

        Bomba bomba = (Bomba) e.getElementoEnPosicion(renglon, columna);
        if (bomba != null) {
            bomba.explotar();
        } else {
            System.out.println("No hay bomba en esa posicion.");
        }

        // Mostrar el estado del escenario despues de la explosion
        System.out.println("Estado del escenario despues de la explosion:");
        System.out.println(e);

        // Guardar la configuracion actual del escenario en el archivo
        guardarConfiguracion(e, "configuracion.txt");
    }

    // Metodo para leer la configuracion desde un archivo
    public static void leerConfiguracion(Escenario escenario, String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String tipoElemento = partes[0];
                int renglon = Integer.parseInt(partes[1]);
                int columna = Integer.parseInt(partes[2]);

                switch (tipoElemento) {
                    case "Roca":
                        escenario.agregarElemento(new Roca(escenario, new Posicion(renglon, columna)));
                        break;
                    case "Extraterrestre":
                        escenario.agregarElemento(new Extraterrestre("Alien", escenario, new Posicion(renglon, columna)));
                        break;
                    case "Bomba":
                        int radio = Integer.parseInt(partes[3]);
                        escenario.agregarElemento(new Bomba(escenario, new Posicion(renglon, columna), radio));
                        break;
                    case "Terricola":
                        escenario.agregarElemento(new Terricola("Ripley", escenario, new Posicion(renglon, columna)));
                        break;
                    default:
                        System.out.println("Elemento desconocido: " + tipoElemento);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuracion.");
            e.printStackTrace();
        }
    }

    // Metodo para guardar la configuracion actual del escenario en un archivo
    public static void guardarConfiguracion(Escenario escenario, String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Elemento e = escenario.getElementoEnPosicion(i, j);
                    if (e != null) {
                        if (e instanceof Roca) {
                            bw.write("Roca " + i + " " + j + "\n");
                        } else if (e instanceof Extraterrestre) {
                            bw.write("Extraterrestre " + i + " " + j + "\n");
                        } else if (e instanceof Bomba) {
                            Bomba bomba = (Bomba) e;
                            bw.write("Bomba " + i + " " + j + " " + bomba.getRadio() + "\n");
                        } else if (e instanceof Terricola) {
                            bw.write("Terricola " + i + " " + j + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de configuracion.");
            e.printStackTrace();
        }
    }
}

