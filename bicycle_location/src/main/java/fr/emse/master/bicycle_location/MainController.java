package fr.emse.master.bicycle_location;

import java.io.File;
import java.util.ArrayList;

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
		org.apache.jena.rdf.model.Model model_static = ModelFactory.createDefaultModel();
		org.apache.jena.rdf.model.Model model_dynamic = ModelFactory.createDefaultModel();

		model_static.read("ttl_static.ttl");
		model_dynamic.read("ttl_dynamic.ttl");

		StmtIterator liste_dynamic = model_dynamic.listStatements();
		StmtIterator liste_static = model_static.listStatements();
		
		m.addAttribute("saint_etienne_static", liste_static);
		m.addAttribute("saint_etienne_dynamic", liste_dynamic);
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
