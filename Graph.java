package graph;

import java.awt.Label;
import java.util.ArrayList;

/**
 * Graph Class, define the graph.
 * @author Greg
 */
public class Graph {
	private char label;
	public int getG_id() {
		return G_id;
	}
	public void setG_id(int g_id) {
		G_id = g_id;
	}
	private int G_id;//graph ID
	private ArrayList<Node> graph=new ArrayList<Node>();//node list
	public ArrayList<Node> getGraph() {
		return graph;
	}
	public void setGraph(ArrayList<Node> graph) {
		this.graph = graph;
	}
}
