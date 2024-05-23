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
        // Grafo 1: Sin ciclos
        Grafo<String, String, Integer> g1 = new Grafo<>();
        g1.insertarVertice("A", "A");
        g1.insertarVertice("B", "B");
        g1.insertarArista("A", "B", 1);

        // Grafo 2: Un ciclo simple
        Grafo<String, String, Integer> g2 = new Grafo<>();
        g2.insertarVertice("A", "A");
        g2.insertarVertice("B", "B");
        g2.insertarArista("A", "B", 1);
        g2.insertarArista("B", "A", 1);

        // Grafo 3: Un ciclo con un vértice adicional sin ciclos
        Grafo<String, String, Integer> g3 = new Grafo<>();
        g3.insertarVertice("A", "A");
        g3.insertarVertice("B", "B");
        g3.insertarVertice("C", "C");
        g3.insertarArista("A", "B", 1);
        g3.insertarArista("B", "A", 1);
        g3.insertarArista("C", "A", 1);

        // Grafo 4: Dos ciclos separados
        Grafo<String, String, Integer> g4 = new Grafo<>();
        g4.insertarVertice("A", "A");
        g4.insertarVertice("B", "B");
        g4.insertarVertice("C", "C");
        g4.insertarVertice("D", "D");
        g4.insertarArista("A", "B", 1);
        g4.insertarArista("B", "A", 1);
        g4.insertarArista("C", "D", 1);
        g4.insertarArista("D", "C", 1);
        // Grafo 5: Ciclo complejo
        Grafo<String, String, Integer> g5 = new Grafo<>();
        g5.insertarVertice("A", "A");
        g5.insertarVertice("B", "B");
        g5.insertarVertice("C", "C");
        g5.insertarVertice("D", "D");
        g5.insertarVertice("E", "E");
        g5.insertarArista("A", "B", 1);
        g5.insertarArista("B", "C", 1);
        g5.insertarArista("C", "D", 1);
        g5.insertarArista("D", "E", 1);
        g5.insertarArista("E", "A", 1);
        g5.insertarArista("A", "C", 1);
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        System.out.println(g4);
        System.out.println(g5);

        System.out.println("Ciclos en g1: " + new TestCiclicidad<String, String, Integer>().testCiclos(g1));  // Debería ser FALSE
        System.out.println("Ciclos en g2: " + new TestCiclicidad<String, String, Integer>().testCiclos(g2));  // Debería ser TRUE
        System.out.println("Ciclos en g3: " + new TestCiclicidad<String, String, Integer>().testCiclos(g3));  // Debería ser TRUE
        System.out.println("Ciclos en g4: " + new TestCiclicidad<String, String, Integer>().testCiclos(g4));  // Debería ser TRUE
        System.out.println("Ciclos en g5: " + new TestCiclicidad<String, String, Integer>().testCiclos(g5));  // Debería ser TRUE


    

        System.out.println("*** FIN ***");
    }
}
