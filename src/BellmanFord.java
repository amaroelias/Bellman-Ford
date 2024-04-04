import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BellmanFord {
    private static final double EPSILON = 1E - 14;
    private  double[] distTo; // distTo[v] = distance of shortest s->v path
    private int[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private DirectedEdge[] aresta; // queue of edges to relax
    private int cost; // number of calls to relax()
    private int V;
    private int E;
    private int s;

    public BellmanFord(String filepath, int source) {
        s = source;

        readImput(filepath);
        Inicializa();

        int i, j;
        for(i = 1;i < V;++i) {
            for(j = 0;j < E;++j) {
                relax(aresta[j]);
            }
        }
        for(i = 0;i < V;++i) {
            if(hasPathTo(i)) {
                System.out.printf("Rota de %d a %d (%5 .2f)",s,i,
        distTo[i]);

                printPathTo(i);
                System.out.println();
            }
        }
        if(!negative()) {
            System.out.print("\nNão há Ciclos Negativos\n");
        }
    }

    private boolean negative() {
        int v, w;
        double peso;
        boolean negative = false;
        for(int i = 0;i < E;++i) {
            v = aresta[i].from();
            w = aresta[i].to();
            peso = aresta[i].weight();

            if (distTo[w] > distTo[v] + peso + EPSILON) {
                negative = true;
                System.out.printf("\nCiclo Negativo entre %d e %d", v,w);
            }
        }
        return negative;
    }

    public void printPathTo(int v)
        {
            if(v == s) {
                System.out.print("\t");
                System.out.print(s);
                return;
            }
            printPathTo(edgeTo[v]);
            System.out.print(" -> ");
            System.out.print(v);
        }

    public void Inicializa() {
        for(int i = 0;i < V;i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = -1;
        }
        distTo[s] = 0.0;
    }

    private void relax(DirectedEdge aresta) {
        int v = aresta.from();
        int w = aresta.to();
        double peso = aresta.weight();
        if(distTo[w] > distTo[v] + peso + EPSILON) {
            distTo[w] = distTo[v] + peso;
            edgeTo[w] = v;
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public void readImput(String filepath) {
        Scanner sc;
        File file = null;
        String line;
        int a, b;
        double cost;
        StringTokenizer tk;
        try {
            file = new File(filepath);
            sc = new Scanner(file);
            if(sc.hasNext())
                V = sc.nextInt();
             else {
                System.out.println("\nArquivo inválido");
                System.exit(0);
            }

            if(sc.hasNext())
                E = sc.nextInt();
            else {
                System.out.println("\nArquivo inválido");
                System.exit(0);
            }

            /*
            private double[] distTo;                           // distTo[v] =
    distance of shortest s ->v path
                                   private DirectedEdge[] edgeTo;
                                   private DirectedEdge[] aresta;
             */
            distTo = new double[V];
            edgeTo = new int[V];
            aresta = new DirectedEdge[E];
        }

    }





}
