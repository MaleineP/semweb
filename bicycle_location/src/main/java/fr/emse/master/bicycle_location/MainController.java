package fr.emse.master.bicycle_location;

import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController
{
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String gotToIndex(Model m) {
		org.apache.jena.rdf.model.Model model = ModelFactory.createDefaultModel();
        return "index.html";
    }
	
}
