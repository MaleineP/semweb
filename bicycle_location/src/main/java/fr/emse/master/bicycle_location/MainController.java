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

@Controller
public class MainController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotToIndex(Model m) {
		/*
		 * try { OWLOntology ontology = this.loadOntology(); } catch (OWLException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("launch");
		String[] cities = {"st_etienne"};
		String query_path = "src/main/resources/queries/dynamic/";
		String ttl_path = "src/main/resources/ttl/dynamic/";
		for(int i=0; i<cities.length; i++) {
			System.out.println(i);
			new create_ttl("target/classes/sparql-generate.jar", query_path+cities[i]+".sparql", ttl_path+cities[i]+".ttl");
		}
		

		org.apache.jena.rdf.model.Model model_static = ModelFactory.createDefaultModel();
		org.apache.jena.rdf.model.Model model_dynamic = ModelFactory.createDefaultModel();

		model_static.read("ttl_static.ttl");
		model_dynamic.read("ttl_dynamic.ttl");

		ArrayList<StmtIterator> liste_static = new ArrayList<StmtIterator>();
		ArrayList<StmtIterator> liste_dynamic = new ArrayList<StmtIterator>();
		ResIterator iter = model_static.listSubjects();
		List<Resource> list_iter_static = iter.toList();
		List<Resource> list_iter_dynamic = model_dynamic.listSubjects().toList();

		ArrayList<List<Statement>> saint_etienne = new ArrayList<List<Statement>>();

		for (Resource subject : list_iter_static) {
			StmtIterator tmp = model_static.listStatements(subject, (Property) null, (RDFNode) null);
			liste_static.add(tmp);
			break;
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

		m.addAttribute("saint_etienne_d", liste_dynamic);
		m.addAttribute("saint_etienne_s", liste_static);
		return "index.html";
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
