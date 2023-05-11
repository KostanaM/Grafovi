package grafovi;

import java.util.*;

class Graph<T> {
	/*
	 * U Javi, Graf je struktura podataka koja čuva određene podatke. Koncept grafa
	 * je ukraden iz matematike koja zadovoljava potrebe oblasti informatike.
	 * Predstavlja mrežu koja povezuje više tačaka jedna sa drugom. Svaki graf se
	 * sastoji od ivica i temena (koji se nazivaju i čvorovi). Svaki vrh i ivica
	 * imaju relaciju. Gde vrh predstavlja podatke, a ivica odnos između njih. Teme
	 * je označeno krugom sa oznakom na njima. Ivice su označene linijom koja
	 * povezuje čvorove (temene).
	 */

	// kreiranje objekta klase Map
	private Map<T, List<T>> map = new HashMap<>();

	// metod dodaje novi vrh grafu
	public void addNewVertex(T s) {
		map.put(s, new LinkedList<T>());
	}

	// metod dodaje ivicu između izvora i odredišta
	public void addNewEdge(T source, T destination, boolean bidirectional) {
//      
		if (!map.containsKey(source))
			addNewVertex(source);
		if (!map.containsKey(destination))
			addNewVertex(destination);
		map.get(source).add(destination);
		if (bidirectional == true) {
			map.get(destination).add(source);
		}
	}

	// metod broji broj vrhova
	public void countVertices() {
		System.out.println("Ukupan broj vrhova: " + map.keySet().size());
	}

	// metod broji broj ivica
	public void countEdges(boolean bidirection) {

		// promenljiva za čuvanje broja ivica
		int count = 0;
		for (T v : map.keySet()) {
			count = count + map.get(v).size();
		}
		if (bidirection == true) {
			count = count / 2;
		}
		System.out.println("Ukupan broj ivica:" + count);
	}

	// proverava da li graf ima vrh ili ne
	public void containsVertex(T s) {
		if (map.containsKey(s)) {
			System.out.println("Graf sadrži/ " + s + " /kao vrh.");
		} else {
			System.out.println("Graf ne sadrži/ " + s + " /kao vrh.");
		}
	}

	// proverava da li graf ima ivicu ili ne
	// gde su s i d dva parametra koja predstavljaju izvor (vrh) i odredište (temen)
	public void containsEdge(T s, T d) {
		if (map.get(s).contains(d)) {
			System.out.println("Grafikon ima ivicu između " + s + " i " + d + ".");
		} else {
			System.out.println("Nema ivice između " + s + " i " + d + ".");
		}
	}

	// štampa listu susednosti svakog temena
	public String toString() {
		StringBuilder builder = new StringBuilder();

		// foreach loop koja se ponavlja preko ključeva
		for (T v : map.keySet()) {
			builder.append(v.toString() + ": ");

			// foreach loop za dobijanje vrhova
			for (T w : map.get(v)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}
		return (builder.toString());
	}
}

// kreiranje klase u kojoj smo implementirali kod drajvera
public class Main {
	public static void main(String args[]) {

		// kreiranje objekta klase Graph
		Graph graph = new Graph();

		// dodavanje ivica grafu
		graph.addNewEdge(0, 1, true);
		graph.addNewEdge(0, 4, true);
		graph.addNewEdge(1, 2, true);
		graph.addNewEdge(1, 3, false);
		graph.addNewEdge(1, 4, true);
		graph.addNewEdge(2, 3, true);
		graph.addNewEdge(2, 4, true);
		graph.addNewEdge(3, 0, true);
		graph.addNewEdge(2, 0, true);

		// štampa matricu susednosti koja predstavlja graf
		System.out.println("Lista susednosti za grafikon:\n" + graph.toString());

		// prebrojava broj vrhova u grafu
		graph.countVertices();
		// prebrojava broj ivica u grafu
		graph.countEdges(true);

		// proverava da li postoji ivica ili ne između dva navedena vrha
		graph.containsEdge(3, 4);
		graph.containsEdge(2, 4);

		// proverava da li je vrh prisutan ili ne
		graph.containsVertex(3);
		graph.containsVertex(5);
	}
}