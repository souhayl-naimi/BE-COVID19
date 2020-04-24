# BE-COVID19
Rest API (Spring Boot Application) for developers
ce api aide a scrapper deux sites : covidmaroc.ma (traçage des cas) & hcp.ma (pour horloge de la population), 
en utilison une library qui s'apelle jsoup.jar.
SCRAPPER ET STOCKER dans la base de donnees (lire le CODE)
localhost:yourport/regions (les regions)
localhost:yourport/pop (le population)
localhost:yourport/statuses (les cas)
le scrapper est automatisé avec l'annotation @Scheduler de la librarie Awaitility
