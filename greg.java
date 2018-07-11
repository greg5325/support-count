package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class greg {

	private static final int MAX_INT = 1000000;
	static Graph datagraph=new Graph();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadDataFile();
		ReadQueryFile();
//		System.out.print("sysout");
	}

	public static void ReadDataFile() {

		try {
			// fis = new FileInputStream("D:\\newdata\\edges.txt");
			// System.out.println(fis.read(buffer));
			// fis.close();
			File filename = new File("D:\\newdata\\data.csv"); // 要读取以上路径的input。txt文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			line = br.readLine();
			while (line != null) {
				if(line.startsWith("v")) {
					String[] a=line.split(" ");
					Node node=new Node();
					node.setId(Integer.parseInt(a[1]));
					node.setLabel(Integer.parseInt(a[2]));
					datagraph.getGraph().add(node);
//					System.out.println(a[2]);
				}
				if(line.startsWith("e")) {
					String[] a=line.split(" ");
					int inedge=Integer.parseInt(a[1]);
					int outedge=Integer.parseInt(a[2]);
					Edge e=new Edge(datagraph,datagraph.getGraph().get(inedge),datagraph.getGraph().get(outedge),1);
//					datagraph.getGraph().get(inedge).getOutEdges().add(e);
//					datagraph.getGraph().get(outedge).getInEdges().add(e);
				}
//				System.out.println(line);
//				String[] a=line.split("身");
//				System.out.println(a[0]);
				
				line = br.readLine(); // 一次读入一行数据
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 二进制边
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ReadQueryFile() {

		try {
			// fis = new FileInputStream("D:\\newdata\\edges.txt");
			// System.out.println(fis.read(buffer));
			// fis.close();
			File filename = new File("D:\\newdata\\oomap-newquery6.txt"); // 要读取以上路径的input。txt文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			line = br.readLine();
			Graph query=new Graph();
			while (line != null) {
				if(line.startsWith("t")) {
					String[] a=line.split(" ");
					query.setG_id(Integer.parseInt(a[2]));
				}
				if(line.startsWith("v")) {
					String[] a=line.split(" ");
					Node node=new Node();
					node.setId(Integer.parseInt(a[1]));
					node.setLabel(Integer.parseInt(a[2]));
					query.getGraph().add(node);
//					System.out.println(a[2]);
				}
				if(line.startsWith("e")) {
					String[] a=line.split(" ");
					int inedge=Integer.parseInt(a[1]);
					int outedge=Integer.parseInt(a[2]);
					Edge e=new Edge(query,query.getGraph().get(inedge),query.getGraph().get(outedge),1);
//					datagraph.getGraph().get(inedge).getOutEdges().add(e);
//					datagraph.getGraph().get(outedge).getInEdges().add(e);
				}
//				System.out.println(line);
//				String[] a=line.split("身");
//				System.out.println(a[0]);
				
				line = br.readLine(); // 一次读入一行数据
			}
			int support=count(matching(query));
			System.out.println("support="+support);
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 二进制边
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<Integer>> matching(Graph querygraph) {

		ArrayList<ArrayList<Integer>> can_simulate = new ArrayList<ArrayList<Integer>>();

		Iterator<Node> it2 = querygraph.getGraph().iterator();
		while (it2.hasNext()) {
			Node query = it2.next();
			Iterator<Node> it1 = datagraph.getGraph().iterator();
			ArrayList<Integer> simulate = new ArrayList<Integer>();
			while (it1.hasNext()) {
				Node data = it1.next();
				int cansimulate = 0;
				if (data.getLabel() == query.getLabel()) {
					cansimulate = 1;
					int datain[] = new int[50];
					int dataout[] = new int[50];
					for (int i = 0; i < 50; i++) {
						datain[i] = 0;
						dataout[i] = 0;
					}
					Iterator<Edge> it3 = query.getInEdges().iterator();
					Iterator<Edge> it4 = data.getInEdges().iterator();
					while (it4.hasNext()) {
						datain[it4.next().source.getLabel()]++;
					}
					while (it3.hasNext()) {
						Edge inedge = it3.next();
						datain[inedge.source.getLabel()]--;
						if (datain[inedge.source.getLabel()] < 0) {
							cansimulate = 2;
						}
					}
					Iterator<Edge> it5 = query.getOutEdges().iterator();
					Iterator<Edge> it6 = data.getOutEdges().iterator();
					while (it6.hasNext()) {
						dataout[it6.next().source.getLabel()]++;
					}
					while (it5.hasNext()) {
						Edge outedge = it5.next();
						dataout[outedge.target.getLabel()]--;
						if (dataout[outedge.source.getLabel()] < 0) {
							cansimulate = 2;
						}
					}
				}
				if (cansimulate == 1) {
					simulate.add(data.getId());
				}
			}
			can_simulate.add(simulate);
		}
		return can_simulate;
	}
	
	public static int count(ArrayList<ArrayList<Integer>> can_simulate) {
		Iterator<ArrayList<Integer>> it1 = can_simulate.iterator();
		int count=MAX_INT;
		while (it1.hasNext()) {
			int temp=it1.next().size();
			if(count>temp) {
				count=temp;
			}
		}
		return count;
	}
}
