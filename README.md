<h1>READ ME</h1>
<h2>Welkom</h2>
Welkom bij mijn README voor mijn Systeem Applicatie: **Pokebase**  
Deze applicatie is gemaakt om verschillende Pokémons op te slaan met allerlei verschillende informatie over de Pokémon.

<h2>Gebruik</h2>
Doormiddel van het downloaden van de <b>JAR</b> file in out/artifacts/Pokebase_jar, kan je de applicatie starten.
<br/>
Voordat je start moet je wel voor zorgen dat je ook de database hebt geimporteerd dat inbegrepen is in de database folder,
Hiervoor kan je het best <b>PHPMYADMIN</b> gebruiken om deze database te hosten. Zorg hierbij wel voor dat de database dezelfde naam behoud: pokedexbase.
<br/>
Mocht je de naam aanpassen zou je het gehele project moeten downloaden en handmatig de database naam veranderen in controllers/DatabaseController.
<br/>

```js
this.connectionCode = "jdbc:mysql://localhost:3306/pokedexbase?user=root&password=";
```

<br/>

<h2>Roadmap</h2>

- [x] Basisfunctionaliteit (CRUD-operaties)
- [x] Databasekoppeling via MySQL
- [ ] Toevoegen van unit- en integratietests
- [ ] Mogelijkheid om zones en types bij te werken
- [ ] UI/UX-styling van de applicatie
- [ ] Zoek- en filterfunctionaliteiten op naam, type en zone

<h2>Communicatiestrategie</h2>
Tijdens de ontwikkeling wordt gebruikgemaakt van:

- **Versiebeheer** via GitHub (issues, pull requests)
- **Inline commentaar** in de broncode
- **Documentatie** zoals deze README

Bij samenwerking met anderen:
- **E-mail** voor belangrijke updates en formele communicatie

<h2>Evaluatiemethoden</h2>

De applicatie wordt geëvalueerd op de volgende manieren:

- **Functionele tests**: Werkt alle functionaliteit zoals bedoeld?
- **Gebruikerstests**: Feedback over gebruiksvriendelijkheid
- **Codekwaliteit**: Zijn best practices en conventies gevolgd?
- **Prestatietests**: Hoe gaat de applicatie om met veel data?
- **Feedbackrondes**: Van docenten, medestudenten en eventueel gebruikers
