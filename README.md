<h1>READ ME</h1>
<h2>Welkom</h2>
Welkom bij mijn READ ME voor mijn Systeem Applicatie: Pokebase
Deze applicatie is gemaakt om verschillende Pokémons op te slaan met allerlei verschillende informatie over de Pokémon

<h2>Gebruik</h2>
Doormiddel van het downloaden van de JAR file in out/artifacts/Pokebase_jar, kan je de applicatie starten.
<br/>
Voordat je start moet je wel voor zorgen dat je ook de database hebt geimporteerd dat inbegrepen is in de database folder,
Hiervoor kan je het best PHPMYADMIN gebruiken om deze database te hosten. Zorg hierbij wel voor dat de database dezelfde naam behoud: pokedexbase.
<br/>
Mocht je de naam aanpassen zou je het gehele project moeten downloaden en handmatig de database naam veranderen in controllers/DatabaseController.
<br/>

```js
this.connectionCode = "jdbc:mysql://localhost:3306/pokedexbase?user=root&password=";
```

<br/>

<h2>Roadmap</h2>
<ul>
  <li>Toevoegen van tests</li>
  <li>Aanpassen van update om ook zones en types aan te passen</li>
  <li>Styling van de applicatie</li>
</ul>
