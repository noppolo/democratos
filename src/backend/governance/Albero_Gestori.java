package backend.governance;

import java.util.Collection;

import edu.uci.ics.jung.graph.Forest;

public class Albero_Gestori {
	/**
	 * the graph
	 */
	private Forest<GestoreDiAssemblea, String> tree;

	public Albero_Gestori() {
		// TODO Auto-generated constructor stub
		tree = new DelegateForest<>();
	}

	public void addGestoreDiAssemblea(GestoreDiAssemblea gest) {
		String name = gest.getName();

		// TODO Auto-generated method stub

		String parent_name = null;
		if (!name.endsWith(".")) {
			System.out
					.println("!!! addGestoreDiAssemblea: ERRORE NELLA SINTASSI DI name :"
							+ name);
			System.exit(-1);
		}

		// TODO controllare eventuali ripetizioni
		tree.addVertex(gest);

		GestoreDiAssemblea parent_gest = null;

		
		if (name.contentEquals(".")) {
			/*--- Non aggiungiamo il vertice con il padre (perche' non esiste*/
		} else {
			parent_name = constructNameOfParent(name);

			Collection<GestoreDiAssemblea> vertices = tree.getVertices();

			for (GestoreDiAssemblea g : vertices) {
				if (parent_name.contentEquals(g.getName())) {
					parent_gest = g;
					break;
				}
			}
			if (parent_gest == null) {
				System.out
						.println("!!! addGestoreDiAssemblea: ERRORE  NELLA RICERCA DI parent_name :["
								+ parent_name
								+ "] DURANTE L'INSERIMENTO DI ["
								+ name
								+ "] "
								+ " Controllare l'ordine nel file di configurazione.");

				System.exit(-1);
			}

			String childParental = parent_name + "->" + name;

/*			System.out.println("Albero_gestori.addGestoreDiAssemblea: [" + childParental + "] "
					+ parent_name + " e' padre di " + name + "");*/

			/**/
			tree.addEdge(childParental, parent_gest, gest);
		}
	}

	private String constructNameOfParent(String name) {
		// TODO Auto-generated method stub
		int prelast;
		if (!name.endsWith(".")) {
			System.out
					.println("!!! constructNameOfParent: WARNING NELLA SINTASSI DI name :"
							+ name);
			prelast = name.lastIndexOf('.');
		} else {
			String tmp = name.substring(0, name.length() - 1);
			prelast = tmp.lastIndexOf('.');
		}
		return name.substring(0, prelast + 1);
	}

	public Forest<GestoreDiAssemblea, String> getTree() {
		// TODO Auto-generated method stub
		return tree;
	}



}
