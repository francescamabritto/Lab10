package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;


public class Model {
	
	private Graph <Author, DefaultEdge> graph;
	public List <Author> autori;
	private List <Paper> pubblicazioni;
	private PortoDAO dao;
	private AuthorIdMap authormap;
	private PaperIdMap papermap;

	public Model() {
		
	}
	
	public void createGraph() {
		List<Author> autoriPubblicazioni;
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		this.dao = new PortoDAO();
		this.authormap = new AuthorIdMap();
		this.papermap = new PaperIdMap();

		this.pubblicazioni = dao.getAllPapers(papermap);
		this.autori = dao.getAllAuthors(authormap);
				
		for(Author a: autori) {
			this.graph.addVertex(a);
		}
		
		for(Paper p : pubblicazioni) {
			autoriPubblicazioni = dao.getPaperAuthors(p, authormap);
			if(autoriPubblicazioni.size()>1) {
				for(int i = 0; i<autoriPubblicazioni.size(); i++) {
					if((autoriPubblicazioni.size()>2)&&(i == autoriPubblicazioni.size()-1))
						graph.addEdge(autoriPubblicazioni.get(0), autoriPubblicazioni.get(i));
					else if(i < autoriPubblicazioni.size()-1)
						graph.addEdge(autoriPubblicazioni.get(i), autoriPubblicazioni.get(i+1));
				}
			}
		}
		//System.out.println(graph);
	}

	public List<Author> getCoautori(Author autore) {
		List<Author> coautori = Graphs.neighborListOf(graph, autore);
		return coautori;
	}
	
	// metodo che prende i cognomi degli autori
	public List<String> toLastname(List<Author> autori){
		List<String> res = new ArrayList<>();
		for(Author a: autori) {
			res.add(a.getLastname());
		}
		return res;
	}
	
	// metodo che ritorna una lista senza i co autori trovati nel punto precedente
	public List<Author> getAltriAutori(List<Author> coautori){
		List<Author> altri = new ArrayList<>(this.autori);
		for(Author a: coautori) {
			altri.remove(a);
		}
		return altri;
	}
	
	// ESERCIZIO 2
	public List<Paper> getSequenzaArticoli(Author start, Author arr){
		List <Paper> articoli = new ArrayList<>();
		if(this.getCamminoMinimo(start, arr).isEmpty()) {
			throw new NullPointerException();
			
		}
		for(int i = 0; i<this.getCamminoMinimo(start, arr).size()-1; i++) {
			Author a1 = this.getCamminoMinimo(start, arr).get(i);
			Author a2 = this.getCamminoMinimo(start, arr).get(i+1);
			articoli.add(dao.getPaperFromTwoAuthors(a1, a2, papermap));
		}
		return articoli;
	}
	
	
	
	public List<Author> getCamminoMinimo(Author start, Author arr){
		
		ShortestPathAlgorithm<Author,DefaultEdge> spa = new DijkstraShortestPath<Author,DefaultEdge>(graph);
		GraphPath <Author,DefaultEdge> gp = spa.getPath(start, arr);
		
		return gp.getVertexList();
	}
	
	
	/*
	// ricorsione per il BreadthFirstIterator
	
	private void ricorsione(Author start, Author arr, Author tmp, List<Author> autoriCamminoMinimo, List<Author> parziale) {
		BreadthFirstIterator<Author, DefaultEdge> bfi = new BreadthFirstIterator<>(this.graph, start);
		//List<Author> parziale = new ArrayList<>();
		
		//Author tmp = bfi.next();
		int pass = 10000;
		
		if(tmp.equals(arr)) {
			if(parziale.size() < pass) {
				pass = parziale.size();
				autoriCamminoMinimo = parziale;
			}
			
		}
		
		while(bfi.hasNext() && !tmp.equals(arr)) {
			tmp = bfi.next();
			parziale.add(tmp);
			ricorsione(tmp, arr, tmp, autoriCamminoMinimo, parziale);
		}	
	}
	
	*/
	
	

}
