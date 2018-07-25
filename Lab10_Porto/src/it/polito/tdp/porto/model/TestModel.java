package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println("TODO: write a Model class and test it!");
		
		model.createGraph();
		
		System.out.println("autori:  "+ model.autori);
		
		Author a = new Author(1847, "Lioy", "Antonio");
		//System.out.println(model.getCoautori(a).toString());
	}
	

}
