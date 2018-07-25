package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
//		System.out.println(pd.getAutore(85));
//		System.out.println(pd.getArticolo(2293546));
//		System.out.println(pd.getArticolo(1941144));
		AuthorIdMap authormap = new AuthorIdMap();
//		System.out.println(pd.getAllAuthors(authormap ));
		PaperIdMap papermap = new PaperIdMap();
//		System.out.println(pd.getAllPapers(papermap));
		
		Paper p = new Paper(2379363, "Network-level access control policy analysis and transformation","1063-6692", "IEEE-ACM TRANSACTIONS ON NETWORKING", "article", "TYPES2");
//		System.out.println(pd.getPaperAuthors(p, authormap));
		Author a1 = new Author(719, "Milanese", "Mario");
		Author a2 = new Author(2185, "Taragna", "Michele");		
		System.out.println(pd.getPaperFromTwoAuthors(a1, a2, papermap));
	}

}
