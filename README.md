### semweb Pierre Hugues and Maleine Patural

# Run the script

After some tests with a runnable jar file, we came to the conclusion that it is better to use it as-is

- First, execute the MainApplication class. Since every path are relatives, it should not cause any problem

- Then, go to localhost:8080 in your browser

- Finally, you should see the main page where you can select the city you want information from. You will find it as 2 tables : one giving information about the stations and one other giving realtime indications about the number of bikes available in each station, the number of free slots...


# Integration of RDF

We have been transforming the information distributed by a bicycle agency to rdf triples using sparql-generate. 

  - Given a link pointing to a json file containing all the informations needed, it creates an ttl file where all station are blank nodes with multiple proprieties. Each query need to be done first to fit the json architecture, which may differ from one city to another. Those queries are stocked in ressources/queries/dynamic(or static). Next, they are executed by the sparql-generate.jar to write the ttl files in ressources/ttl/dynamic(or static). The loading may be long if the dataset is too big. So, when it is possible (St Etienne for example), it is better to load the static data only once for optimization. Then only the dynamic is loaded when the webpage is refreshed to provide realtime data.

  - Then we import this ttl file into java thanks to the Jena package. In the MainApplication class, we load the ttl file as a list of triples.

  - Finally, it is transformed to visual information in the front-page of the website and also to RDFa inside the HTLM code. Each statement is merged in a list with others with the same the subject before being displayed into the html file.
