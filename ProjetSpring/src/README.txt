
Voici le rendu de notre TP Spring.

Nous avons commenc� par refactorer le code du serveur pour s�parer en 2 classes la partie d'authentification de la partie de gestion des chatRooms.
Ensuite, nous avons tent� de s�parer la partie pr�sentation d' lIHM du traitement li� aux �v�nements, en d�pla�ant autant que possible du code dans les commandes.
Ca se voit surtout dans la classe CommandeServChatRoomPostMessage, m�me s'il est difficile de s�parer ce que doit faire l'IHM de ce qu'elle ne doit pas faire.

Ensuite, nous avons am�lior� la communication entre les c�t�s clients et serveur, en ajoutant au package client d'autres commandes, ayant pour cible le serveur,
   qui s'est charg� de relayer les appels vers ses sous parties capables de r�pondre aux requ�tes.
Cela �vitait une communication par appels distants entre le client et le serveur.

Au fur et � mesure de nos avancements, nous avons remplac� les param�tres des constructeurs,
   les appels aux setters et les new sauvages dans le code par des instanciations par le fichier xml de Spring.

Puis nous nous sommes occup�s de d�tacher les aspects log du code m�tier de l'authentification avec Spring AoP.


Nous sommes conscients qu'il y aurait des quantit�s d'autres corrections de code possibles pour ce projet,
   mais nous nous sommes limit�s � peu pr�s au sujet pour ne pas d�penser trop de temps, puisque l'essentiel �tait d'apprendre � utiliser certaines des parties de Spring,
   et comprendre � quoi elle servent, et nous pensons avoir atteint cet objectif.


Cordialement, Fran�ois HUVE, Mathieu GUICHAOUA.