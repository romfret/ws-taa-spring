package fr.istic.m2infopro.taa.springPrj;

public class HelloWorld {
	private String name;
	private String tante;
	 
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTante(String tante) {
		this.tante = tante;
	}
 
	public void printHello() {
		System.out.println("Hello ! " + name + "\n" + tante);
	}
}
