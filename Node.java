package graph;

import java.util.ArrayList;

public class Node {
	private int id;
	private int label;
//	private int edges_Direction;//邻接边属性（入边or出边）
	ArrayList<Edge> outEdges=new ArrayList<Edge>();	//出边
	ArrayList<Edge> inEdges=new ArrayList<Edge>();	//入边

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getEdges_Direction() {
//		return edges_Direction;
//	}
//	public void setEdges_Direction(int edges_Direction) {
//		this.edges_Direction = edges_Direction;
//	}
	public ArrayList<Edge> getOutEdges() {
		return outEdges;
	}
	public void setOutEdges(ArrayList<Edge> outEdges) {
		this.outEdges = outEdges;
	}
	public ArrayList<Edge> getInEdges() {
		return inEdges;
	}
	public void setInEdges(ArrayList<Edge> inEdges) {
		this.inEdges = inEdges;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
}
