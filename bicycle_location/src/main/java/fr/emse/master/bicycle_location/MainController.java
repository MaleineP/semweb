package fr.emse.master.bicycle_location;

import java.io.File;

import org.apache.jena.rdf.model.ModelFactory;
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
		/*try {
			OWLOntology ontology = this.loadOntology();
		} catch (OWLException e) {
			e.printStackTrace();
		}*/
		org.apache.jena.rdf.model.Model model = ModelFactory.createDefaultModel();
		model.read("document.ttl") ;
		System.out.println(model.toString());
		m.addAttribute("saint_etienne", null);//rajouter l'objet contenant toutes les informations du Turtle
		
		return "index.html";
	}

	/*public OWLOntology loadOntology() throws OWLException {

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology localOntology = null;

		File file = new File("ontology_bicycle_sharing.owl");

		// loading the ontology
		try {
			localOntology = manager.loadOntologyFromOntologyDocument(file);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		return localOntology;
	}*/

}
