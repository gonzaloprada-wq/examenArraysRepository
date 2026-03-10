package examen;

import java.util.Scanner;

/**
 * Aplicación de consola para analizar mensajes misteriosos.
 * Trabaja con arrays, strings, funciones, validación y menú interactivo.
 */

public class ejemplo2 {

    private static final int OPCION_SALIR = 8;
    private static final int LONGITUD_MINIMA_MENSAJE_LARGO = 20;
    private static final String PREFIJO_ALTERADO = "[ALTERADO] ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] mensajes = crearMensajesIniciales();

        ejecutarAplicacion(scanner, mensajes);

        scanner.close();
    }

    /**
     * Ejecuta el flujo principal de la aplicación.
     *
     * @param scanner  objeto para leer datos por teclado
     * @param mensajes array con los mensajes almacenados
     */
    public static void ejecutarAplicacion(Scanner scanner, String[] mensajes) {
        int opcionSeleccionada;

        do {
            mostrarMenu();
            opcionSeleccionada = leerEntero(scanner, "Elige una opción: ");
            ejecutarOpcion(opcionSeleccionada, scanner, mensajes);
            System.out.println();
        } while (opcionSeleccionada != OPCION_SALIR);
    }

    /**
     * Crea el array inicial de mensajes.
     *
     * @return array con los mensajes de partida
     */
    public static String[] crearMensajesIniciales() {
        return new String[] {
            "LA NIEBLA CUBRE EL PUENTE",
            "NO MIRES DETRAS DE LA PUERTA",
            "EL RELOJ SUENA A MEDIANOCHE",
            "LAS SOMBRAS SABEN TU NOMBRE",
            "TODO MENSAJE DEJA UNA HUELLA",
            "EL ECO REPITE LA VERDAD",
            "ALGUIEN ESCONDE ALGO AQUI",
            "NUNCA APAGUES LA RADIO"
        };
    }

    /**
     * Muestra el menú principal.
     */
    public static void mostrarMenu() {
        System.out.println("ARCHIVO DE LOS ECOS");
        System.out.println("1. Mostrar mensajes");
        System.out.println("2. Contar letra en un mensaje");
        System.out.println("3. Extraer mensaje oculto");
        System.out.println("4. Buscar palabra en todos los mensajes");
        System.out.println("5. Marcar mensaje como alterado");
        System.out.println("6. Mostrar palabra más larga de un mensaje");
        System.out.println("7. Mostrar estadísticas generales");
        System.out.println("8. Salir");
    }

    /**
     * Ejecuta la acción asociada a la opción elegida en el menú.
     *
     * @param opcion   opción elegida
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarOpcion(int opcion, Scanner scanner, String[] mensajes) {
        switch (opcion) {
            case 1:
                mostrarMensajes(mensajes);
                break;
            case 2:
                ejecutarConteoDeLetra(scanner, mensajes);
                break;
            case 3:
                ejecutarExtraccionDeMensajeOculto(scanner, mensajes);
                break;
            case 4:
                ejecutarBusquedaDePalabra(scanner, mensajes);
                break;
            case 5:
                ejecutarMarcadoDeMensajeAlterado(scanner, mensajes);
                break;
            case 6:
                ejecutarBusquedaDePalabraMasLarga(scanner, mensajes);
                break;
            case 7:
                mostrarEstadisticas(mensajes);
                break;
            case 8:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Muestra todos los mensajes con su índice.
     *
     * @param mensajes array de mensajes
     */
    public static void mostrarMensajes(String[] mensajes) {
        for (int i = 0; i < mensajes.length; i++) {
            System.out.println(i + ". " + mensajes[i]);
        }
    }

    /**
     * Comprueba si un índice pertenece al rango válido del array.
     *
     * @param mensajes array de mensajes
     * @param indice   índice a validar
     * @return true si es válido; false en caso contrario
     */
    public static boolean esIndiceValido(String[] mensajes, int indice) {
        return indice >= 0 && indice < mensajes.length;
    }

    /**
     * Lee un número entero por teclado, repitiendo la petición mientras
     * el usuario no introduzca un entero válido.
     *
     * @param scanner objeto para leer por teclado
     * @param mensaje mensaje que se muestra al usuario
     * @return número entero válido
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);

        while (!scanner.hasNextInt()) {
            System.out.print("Error. Introduce un número entero: ");
            scanner.nextLine();
        }

        int numeroLeido = scanner.nextInt();
        scanner.nextLine();
        return numeroLeido;
    }

    /**
     * Lee un índice válido del array de mensajes.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     * @return índice válido
     */
    public static int leerIndiceValido(Scanner scanner, String[] mensajes) {
        int indiceLeido = leerEntero(scanner, "Introduce el índice del mensaje: ");

        while (!esIndiceValido(mensajes, indiceLeido)) {
            indiceLeido = leerEntero(scanner, "Índice no válido. Introduce otro índice: ");
        }

        return indiceLeido;
    }

    /**
     * Cuenta cuántas veces aparece una letra dentro de un mensaje,
     * sin distinguir mayúsculas de minúsculas.
     *
     * @param mensaje texto en el que buscar
     * @param letra   letra a contar
     * @return número de apariciones
     */
    public static int contarLetra(String mensaje, char letra) {
        int cantidadApariciones = 0;
        char letraNormalizada = Character.toUpperCase(letra);

        for (int i = 0; i < mensaje.length(); i++) {
            char caracterActual = Character.toUpperCase(mensaje.charAt(i));

            if (caracterActual == letraNormalizada) {
                cantidadApariciones++;
            }
        }

        return cantidadApariciones;
    }

    /**
     * Devuelve un nuevo texto sin espacios.
     *
     * @param mensaje texto original
     * @return texto sin espacios
     */
    public static String quitarEspacios(String mensaje) {

        String resultado = "";

        for (int i = 0; i < mensaje.length(); i++) {
            char caracterActual = mensaje.charAt(i);

            if (caracterActual != ' ') {
                resultado = resultado + caracterActual;
            }
        }

        return resultado;
    }

    /**
     * Extrae el mensaje oculto formado por los caracteres en posiciones pares
     * del mensaje sin espacios.
     *
     * @param mensaje mensaje original
     * @return mensaje oculto
     */
    public static String extraerMensajeOculto(String mensaje) {

        String mensajeSinEspacios = quitarEspacios(mensaje);
        String mensajeOculto = "";

        for (int i = 0; i < mensajeSinEspacios.length(); i++) {

            if (i % 2 == 0) {
                mensajeOculto = mensajeOculto + mensajeSinEspacios.charAt(i);
            }
        }

        return mensajeOculto;
    }

    /**
     * Cuenta cuántos mensajes contienen una palabra concreta,
     * sin distinguir mayúsculas de minúsculas.
     *
     * @param mensajes array de mensajes
     * @param palabra  palabra a buscar
     * @return número de mensajes que contienen la palabra
     */
    public static int buscarMensajesConPalabra(String[] mensajes, String palabra) {
        int cantidadMensajesEncontrados = 0;
        String palabraNormalizada = normalizarTexto(palabra);

        for (String mensaje : mensajes) {
            String mensajeNormalizado = normalizarTexto(mensaje);

            if (mensajeNormalizado.contains(palabraNormalizada)) {
                cantidadMensajesEncontrados++;
            }
        }

        return cantidadMensajesEncontrados;
    }

    /**
     * Marca un mensaje como alterado añadiendo un prefijo si aún no lo tenía.
     *
     * @param mensajes array de mensajes
     * @param indice   posición del mensaje a marcar
     * @return true si se ha marcado; false si no se ha podido
     */
    public static boolean marcarAlterado(String[] mensajes, int indice) {
        if (!esIndiceValido(mensajes, indice)) {
            return false;
        }

        if (estaMarcadoComoAlterado(mensajes[indice])) {
            return false;
        }

        mensajes[indice] = PREFIJO_ALTERADO + mensajes[indice];
        return true;
    }

    /**
     * Indica si un mensaje ya está marcado como alterado.
     *
     * @param mensaje texto a comprobar
     * @return true si tiene el prefijo de alterado
     */
    public static boolean estaMarcadoComoAlterado(String mensaje) {
        return mensaje.startsWith(PREFIJO_ALTERADO);
    }

    /**
     * Devuelve la palabra más larga de un mensaje.
     * Si hay empate, se devuelve la primera.
     *
     * @param mensaje texto de entrada
     * @return palabra más larga
     */
    public static String palabraMasLarga(String mensaje) {
        String[] palabrasDelMensaje = mensaje.split(" ");
        String palabraMasLargaEncontrada = "";

        for (String palabraActual : palabrasDelMensaje) {
            if (palabraActual.length() > palabraMasLargaEncontrada.length()) {
                palabraMasLargaEncontrada = palabraActual;
            }
        }

        return palabraMasLargaEncontrada;
    }

    /**
     * Muestra estadísticas generales de los mensajes almacenados.
     *
     * @param mensajes array de mensajes
     */
    public static void mostrarEstadisticas(String[] mensajes) {
        int cantidadMensajesAlterados = 0;
        int cantidadTotalDeAes = 0;
        int cantidadMensajesLargos = 0;
        String mensajeMasLargo = "";

        for (String mensaje : mensajes) {
            if (estaMarcadoComoAlterado(mensaje)) {
                cantidadMensajesAlterados++;
            }

            cantidadTotalDeAes += contarLetra(mensaje, 'A');

            if (tieneMasDeVeinteCaracteresSinEspacios(mensaje)) {
                cantidadMensajesLargos++;
            }

            if (mensaje.length() > mensajeMasLargo.length()) {
                mensajeMasLargo = mensaje;
            }
        }

        System.out.println("Total de mensajes: " + mensajes.length);
        System.out.println("Mensajes alterados: " + cantidadMensajesAlterados);
        System.out.println("Mensaje más largo: " + mensajeMasLargo);
        System.out.println("Total de letras 'A': " + cantidadTotalDeAes);
        System.out.println("Mensajes con más de 20 caracteres sin espacios: " + cantidadMensajesLargos);
    }

    /**
     * Comprueba si un mensaje supera la longitud mínima establecida
     * cuando se eliminan los espacios.
     *
     * @param mensaje texto a comprobar
     * @return true si supera la longitud mínima
     */
    public static boolean tieneMasDeVeinteCaracteresSinEspacios(String mensaje) {
        return quitarEspacios(mensaje).length() > LONGITUD_MINIMA_MENSAJE_LARGO;
    }

    /**
     * Convierte un texto a mayúsculas para facilitar comparaciones.
     *
     * @param texto texto original
     * @return texto normalizado
     */
    public static String normalizarTexto(String texto) {
        return texto.toUpperCase();
    }

    /**
     * Gestiona la opción de contar una letra en un mensaje.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarConteoDeLetra(Scanner scanner, String[] mensajes) {
        mostrarMensajes(mensajes);
        int indiceMensaje = leerIndiceValido(scanner, mensajes);
        char letraBuscada = leerCaracter(scanner, "Introduce la letra a buscar: ");

        int cantidadApariciones = contarLetra(mensajes[indiceMensaje], letraBuscada);

        System.out.println("La letra '" + letraBuscada + "' aparece " + cantidadApariciones + " veces.");
    }

    /**
     * Gestiona la opción de extraer el mensaje oculto.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarExtraccionDeMensajeOculto(Scanner scanner, String[] mensajes) {
        mostrarMensajes(mensajes);
        int indiceMensaje = leerIndiceValido(scanner, mensajes);

        String mensajeOculto = extraerMensajeOculto(mensajes[indiceMensaje]);
        System.out.println("Mensaje oculto: " + mensajeOculto);
    }

    /**
     * Gestiona la opción de buscar una palabra en todos los mensajes.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarBusquedaDePalabra(Scanner scanner, String[] mensajes) {
        System.out.print("Introduce la palabra a buscar: ");
        String palabraBuscada = scanner.nextLine();

        int cantidadMensajesEncontrados = buscarMensajesConPalabra(mensajes, palabraBuscada);

        System.out.println("La palabra aparece en " + cantidadMensajesEncontrados + " mensaje(s).");
    }

    /**
     * Gestiona la opción de marcar un mensaje como alterado.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarMarcadoDeMensajeAlterado(Scanner scanner, String[] mensajes) {
        mostrarMensajes(mensajes);
        int indiceMensaje = leerIndiceValido(scanner, mensajes);

        boolean seHaMarcado = marcarAlterado(mensajes, indiceMensaje);

        if (seHaMarcado) {
            System.out.println("Mensaje marcado como alterado.");
        } else {
            System.out.println("No se ha podido marcar el mensaje.");
        }
    }

    /**
     * Gestiona la opción de mostrar la palabra más larga de un mensaje.
     *
     * @param scanner  objeto para leer por teclado
     * @param mensajes array de mensajes
     */
    public static void ejecutarBusquedaDePalabraMasLarga(Scanner scanner, String[] mensajes) {
        mostrarMensajes(mensajes);
        int indiceMensaje = leerIndiceValido(scanner, mensajes);

        String palabraMasLargaEncontrada = palabraMasLarga(mensajes[indiceMensaje]);
        System.out.println("La palabra más larga es: " + palabraMasLargaEncontrada);
    }

    /**
     * Lee un carácter por teclado tomando el primer carácter introducido.
     *
     * @param scanner objeto para leer por teclado
     * @param mensaje mensaje que se muestra al usuario
     * @return carácter leído
     */
    public static char leerCaracter(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String textoLeido = scanner.nextLine();

        while (textoLeido.isEmpty()) {
            System.out.print("Error. Introduce al menos un carácter: ");
            textoLeido = scanner.nextLine();
        }

        return textoLeido.charAt(0);
    }
}