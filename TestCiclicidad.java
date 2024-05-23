public class TestCiclicidad <Clave, InfoVertice, Coste >{
  
  public boolean testCiclos (Grafo<Clave, InfoVertice, Coste> g){
    Lista<Clave> vertices = g.listaVertices();
    if(g.esVacio()) return false;
    int i = 1;
    boolean ciclo = false;
    Clave nodoActual = vertices.consultar(i);
    Lista<Clave> nodosVisitados;
    while(i <= vertices.longitud() && !ciclo ){
      nodosVisitados = new Lista<>();
      nodoActual = vertices.consultar(i);
      if(g.listaSucesores(nodoActual).longitud() > 0 && g.listaPredecesores(nodoActual).longitud() > 0 ) {
        ciclo = testCicloNodo(g, nodoActual, nodoActual, nodosVisitados);
      }
      i++;
    }
    return ciclo;
  }

  public boolean testCicloNodo(Grafo<Clave, InfoVertice, Coste> g, Clave nodoInicio, Clave nodoActual, Lista<Clave> nodosVisitados){
    if(nodoInicio.equals(nodoActual) && nodosVisitados.longitud() > 0) return true; // Si he llegado al mismo y la lista de visitados tiene elementos
    if(g.listaSucesores(nodoInicio).longitud() == 0 || g.listaPredecesores(nodoInicio).longitud() == 0) { //NODO AISLADO
      return false;
    }
    boolean encontrado =  false;
    Lista<Clave> sucesoresActuales = g.listaSucesores(nodoActual);
    int i = 1;
    while(!encontrado && nodosVisitados.longitud() < g.numVertices() && i <= sucesoresActuales.longitud()){
      nodosVisitados.insertar(nodosVisitados.longitud() + 1, nodoActual);
      if(testCicloNodo(g, nodoInicio, sucesoresActuales.consultar(i), nodosVisitados)){
        encontrado = true;
      }
      i++;
    }
    return encontrado;
  }
  
  public static void main(String[] args) {
    Grafo <String, String, Integer> gPrueba = new Grafo<String, String, Integer>();
		
		gPrueba.insertarVertice("A", "A");
		gPrueba.insertarVertice("B", "B");
		gPrueba.insertarVertice("C", "C");
		
		//gPrueba.insertarArista("A", "B", 1);
		gPrueba.insertarArista("A", "C", 1);
		//gPrueba.insertarArista("B", "A", 1);
		gPrueba.insertarArista("C", "B", 1);

    Grafo <String, String, Integer> ggPrueba = new Grafo<String, String, Integer>();
		
		ggPrueba.insertarVertice("A", "A");
		ggPrueba.insertarVertice("B", "B");
		ggPrueba.insertarVertice("C", "C");
		
		ggPrueba.insertarArista("A", "B", 1);
		ggPrueba.insertarArista("A", "C", 1);
		ggPrueba.insertarArista("B", "A", 1);
		ggPrueba.insertarArista("C", "B", 1);
		
		System.out.println(gPrueba);
		System.out.println(ggPrueba);

    
		System.out.println("Ciclos: " + new TestCiclicidad<String, String, Integer>().testCiclos(gPrueba));  //FALSE
		System.out.println("Ciclos: " + new TestCiclicidad<String, String, Integer>().testCiclos(ggPrueba));  //TRUE
		System.out.println("*** FIN ***");
  }
}
