package graph;

import java.util.ArrayList;

public class Node {
	private int id;
	private int label;
//	private int edges_Direction;//�ڽӱ����ԣ����or���ߣ�
	ArrayList<Edge> outEdges=new ArrayList<Edge>();	//����
	ArrayList<Edge> inEdges=new ArrayList<Edge>();	//���

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
