package examen;

import java.util.ArrayList;
import java.util.Scanner;

public class ejemplo1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Solicitar texto y mensaje
		System.out.println("Introduce un texto largo:");
		String texto = sc.nextLine();

		System.out.println("Introduce el mensaje oculto a buscar:");
		String mensaje = sc.nextLine();

		// Normalizar texto y mensaje
		String textoNormalizado = normalizarTexto(texto);
		String mensajeNormalizado = normalizarTexto(mensaje);

		// Buscar mensaje en el texto
		int[] posiciones = buscarMensaje(textoNormalizado, mensajeNormalizado);

		// Mostrar resultados
		System.out.println("\nResultados:");
		System.out.println("El mensaje aparece " + posiciones.length + " veces.");
		if (posiciones.length > 0) {
			System.out.print("Posiciones de inicio: ");
			for (int pos : posiciones) {
				System.out.print(pos + " ");
			}
			System.out.println();
		} else {
			System.out.println("No se encontraron apariciones.");
		}

		// Calcular porcentaje de uso
		double porcentaje = calcularPorcentajeUso(textoNormalizado, mensajeNormalizado, posiciones);
		System.out.printf("Porcentaje de letras utilizadas: %.2f%%\n", porcentaje);

		sc.close();
	}

	// Función 1: Normalizar texto (minúsculas y sin espacios)
	public static String normalizarTexto(String texto) {
		return texto.toLowerCase().replace(" ", "");
	}

	// Función 2: Buscar mensaje y devolver posiciones iniciales
	public static int[] buscarMensaje(String texto, String mensaje) {
		ArrayList<Integer> posiciones = new ArrayList<>();

		for (int i = 0; i <= texto.length() - 1; i++) {
			int idxTexto = i;
			int idxMensaje = 0;

			while (idxTexto < texto.length() && idxMensaje < mensaje.length()) {
				if (texto.charAt(idxTexto) == mensaje.charAt(idxMensaje)) {
					idxMensaje++;
				}
				idxTexto++;
			}

			if (idxMensaje == mensaje.length()) {
				posiciones.add(i);
			}
		}

		// Convertir ArrayList a array
		int[] result = new int[posiciones.size()];
		for (int i = 0; i < posiciones.size(); i++) {
			result[i] = posiciones.get(i);
		}
		return result;
	}

	// Función 3: Calcular porcentaje de letras utilizadas en los mensajes
	// encontrados
	public static double calcularPorcentajeUso(String texto, String mensaje, int[] posiciones) {
		if (posiciones.length == 0)
			return 0.0;

		boolean[] usado = new boolean[texto.length()];

		// Marcar caracteres utilizados
		for (int pos : posiciones) {
			int idxTexto = pos;
			int idxMensaje = 0;

			while (idxTexto < texto.length() && idxMensaje < mensaje.length()) {
				if (texto.charAt(idxTexto) == mensaje.charAt(idxMensaje)) {
					usado[idxTexto] = true;
					idxMensaje++;
				}
				idxTexto++;
			}
		}

		// Contar caracteres usados
		int cont = 0;
		for (boolean b : usado) {
			if (b)
				cont++;
		}

		return (cont * 100.0) / texto.length();
	}
}