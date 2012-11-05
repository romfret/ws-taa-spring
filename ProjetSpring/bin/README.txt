
Voici le rendu de notre TP Spring.

Nous avons commencé par refactorer le code du serveur pour séparer en 2 classes la partie d'authentification de la partie de gestion des chatRooms.
Ensuite, nous avons tenté de séparer la partie présentation d' lIHM du traitement lié aux événements, en déplaçant autant que possible du code dans les commandes.
Ca se voit surtout dans la classe CommandeServChatRoomPostMessage, même s'il est difficile de séparer ce que doit faire l'IHM de ce qu'elle ne doit pas faire.

Ensuite, nous avons amélioré la communication entre les côtés clients et serveur, en ajoutant au package client d'autres commandes, ayant pour cible le serveur,
   qui s'est chargé de relayer les appels vers ses sous parties capables de répondre aux requêtes.
Cela évitait une communication par appels distants entre le client et le serveur.

Au fur et à mesure de nos avancements, nous avons remplacé les paramètres des constructeurs,
   les appels aux setters et les new sauvages dans le code par des instanciations par le fichier xml de Spring.

Puis nous nous sommes occupés de détacher les aspects log du code métier de l'authentification avec Spring AoP.


Nous sommes conscients qu'il y aurait des quantités d'autres corrections de code possibles pour ce projet,
   mais nous nous sommes limités à peu près au sujet pour ne pas dépenser trop de temps, puisque l'essentiel était d'apprendre à utiliser certaines des parties de Spring,
   et comprendre à quoi elle servent, et nous pensons avoir atteint cet objectif.


Cordialement, François HUVE, Mathieu GUICHAOUA.