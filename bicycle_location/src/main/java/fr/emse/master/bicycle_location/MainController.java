package fr.emse.master.bicycle_location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.rulesys.impl.SafeTripleIterator;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotToIndex(Model m) {
		/*
		 * try { OWLOntology ontology = this.loadOntology(); } catch (OWLException e) {
		 * e.printStackTrace(); }
		 */
		m.addAttribute("city", new String());
		return "index.html";
	}
	
	@RequestMapping(value = "/dyn+stat", method = RequestMethod.GET)   // script for cities with dynamic and static values
	public String SaintEtienne(Model m, @RequestParam("city") String city) {
		
		String query_path = "src/main/resources/queries/dynamic/"; // path for the query for dynamic data
		String ttl_path = "src/main/resources/ttl/dynamic/";	// path for the ttl file produced by sparql generate
		
		new create_ttl("src/main/resources/sparql-generate.jar", query_path+city+".sparql", ttl_path+city+".ttl"); // execute the query
		
		org.apache.jena.rdf.model.Model model_static = ModelFactory.createDefaultModel(); 	// initialize the model for static data
		org.apache.jena.rdf.model.Model model_dynamic = ModelFactory.createDefaultModel();	// initialize the model for dynamic data

		model_static.read("src/main/resources/ttl/static/"+ city + ".ttl"); 	// load the ttl file
		model_dynamic.read("src/main/resources/ttl/dynamic/"+ city + ".ttl"); 

		ArrayList<StmtIterator> liste_static = new ArrayList<StmtIterator>();	// initialize the list of triples, each element is a station
		ArrayList<StmtIterator> liste_dynamic = new ArrayList<StmtIterator>();
		ResIterator iter = model_static.listSubjects();
		List<Resource> list_iter_static = iter.toList();
		List<Resource> list_iter_dynamic = model_dynamic.listSubjects().toList();

		//ArrayList<List<Statement>> saint_etienne = new ArrayList<List<Statement>>();

		for (Resource subject : list_iter_static) { // for each station
			StmtIterator tmp = model_static.listStatements(subject, (Property) null, (RDFNode) null); // create all the triples where the subject is the station
			liste_static.add(tmp); // add those triples to the list of station
		}

		for (Resource subject : list_iter_dynamic) {
			StmtIterator tmp = model_dynamic.listStatements(subject, (Property) null, (RDFNode) null);
			liste_dynamic.add(tmp);
		}

		/*Property hasId = model_static
				.getProperty("http://www.semanticweb.org/acer/ontologies/2019/9/bicycle_sharing#has_id");
		Property hasId_d = model_dynamic
				.getProperty("http://www.semanticweb.org/acer/ontologies/2019/9/bicycle_sharing#has_id");

		for (StmtIterator liste_stmt_static : liste_static) {
			StmtIterator tmp = liste_stmt_static;
			RDFNode id = tmp.toModel().listObjectsOfProperty(hasId).toList().get(0);
			System.out.println(id);
			for (StmtIterator liste_stmt_dynamic : liste_dynamic) {
				StmtIterator tmp_bis = liste_stmt_dynamic;
				if (id.equals(tmp_bis.toModel().listObjectsOfProperty(hasId_d).toList().get(0))) {
					List<Statement> tmp_ter = liste_stmt_static.toList();
					tmp_ter.addAll(liste_stmt_dynamic.toList());
					saint_etienne.add(tmp_ter);
					System.out.println(tmp_ter);
					break;
				}
			}
		}*/
		m.addAttribute("city_d", liste_dynamic);
		m.addAttribute("city_s", liste_static);
		return "saint_etienne.html";
	}
	
	@RequestMapping(value = "/dyn", method = RequestMethod.GET) // script for the city : Lyon
	public String Lyon(Model m, @RequestParam("city") String city) {
		
		String query_path = "src/main/resources/queries/dynamic/";
		String ttl_path = "src/main/resources/ttl/dynamic/";
		
		File tmpFile = new File(ttl_path+city+".ttl");
		 //if (tmpFile.exists() == false)  { // the loading is really long
			new create_ttl("src/main/resources/sparql-generate.jar", query_path+city+".sparql", ttl_path+city+".ttl");
		//}
		
		
		
		org.apache.jena.rdf.model.Model model_dynamic = ModelFactory.createDefaultModel(); // there is no static data for this city

		model_dynamic.read("src/main/resources/ttl/dynamic/"+ city +".ttl");

		ArrayList<StmtIterator> liste_dynamic = new ArrayList<StmtIterator>();
		List<Resource> list_iter_dynamic = model_dynamic.listSubjects().toList();

		for (Resource subject : list_iter_dynamic) {
			StmtIterator tmp = model_dynamic.listStatements(subject, (Property) null, (RDFNode) null);
			liste_dynamic.add(tmp);
		}
		
		m.addAttribute("city", liste_dynamic);
		return "lyon.html";
	}

	/*
	 * public OWLOntology loadOntology() throws OWLException {
	 * 
	 * OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	 * OWLOntology localOntology = null;
	 * 
	 * File file = new File("ontology_bicycle_sharing.owl");
	 * 
	 * // loading the ontology try { localOntology =
	 * manager.loadOntologyFromOntologyDocument(file); } catch
	 * (OWLOntologyCreationException e) { e.printStackTrace(); } return
	 * localOntology; }
	 */

}
