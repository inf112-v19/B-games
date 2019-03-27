# Overordnet mål

Å lage en digital versjon av brettspillet RoboRally. Formålet med spillet er å 
passere igjennom alle flaggene på brettet i riktig rekkefølge med sin robot-karakter 
som representerer spilleren på brettet.

Spillet støtter spillere fra 1 til 8. Robotene starter med a viss mengde liv, som de mister 
ved å akkumluere 10 skade eller falle ned i et hull på brettet. De er mulig å reparere 
skade ved å slutte en runde på et reparasjonspunkt eller ved at spilleren velger å slå av 
strømmen på sin robot. Hvor roboten vil bli gjennopprettet dersom den mister et liv 
vil bli oppdatert etterhvert som den lander på enten ett reparasjonspunkt eller flagg.

Bevegelse på brettet blir gjort med programkort som spillerene får utdelt, ifra disse 
kortene må spillerene velge hvilke bevegelser de ønsker at robotene skal utføre. 
En runde består av 5 faser hvor hver fase tilsvarer en bevegelse beskrevet på programkortene. 


# Krav

**Brett:**
	
	- Oppdaterer posisjon til robotene ettersom spillet går sin gang
	- En rute per robot
	- Startposisjoner for robotene, bestemmer prioritet for hendelser som ikke er
      omfattet av prioritet på programkort
	- Vegger, roboter kan ikke bevege seg gjennom vegger og kan ikke fyre av laser 	 	   
      gjennom disse. Er også vegger rundt alle kantene på brettene.
	- Ekspress samlebånd beveger roboter som står på den 2 ruter hver fase. 
	- Vanlig samlebånd beveger roboter som står på den 1 ruter hver fase. 
	- Roboter som kommer over på et samlebånd fra et annet samlebånd vil bli rotert	   
      90 grader i pilens retning når den går gjennom en sving. Roboter som går på 		   
      samlebåndet selv vil ikke bli rotert
	- Laser panel skyter laser (1 skade) mot nærmeste robot som er i skuddlinjen. Kun nærmeste 	   
      robot blir skadet
	- Dyttere flytter roboter som befinner på den mot ruten den vender seg mot
	- Gir roterer roboter som befinner de 90 grader i pilens retning
	- Reparasjonspunkt reparer 1 skade dersom en robot står på den ved slutten av en 	  
      runde. Oppdaterer også gjenopprettingspunkt
	- Flagg blir registert av robotene dersom de befinner seg på de i riktig rekkefølge. 	   
      Oppdaterer også gjenopprettingspunkt
    - (ny) Hull, roboter mister ett liv dersom de faller i et hull på brettet
    - (ny) Spiller(e) (player 1?) skal ha mulighet til å plassere flagg selv på brettet
    - (ny) Flere brett skal kunne settes sammen til et større brett

**Robot:**
	
	- Blir tildelt startposisjon tilfeldig
	- Har en retning den vender seg mot
	- Holder orden på hvor mange liv den har
	- Holder orden på hvor mye skade den har
	- Mister et liv dersom den akkumulerer 10 skade eller faller i et hull på brettet
	- Fjerner all skade som er akkumulert dersom strøm slås av for en runde, fjerner 1 	  
   	  skade dersom den står på et reparasjonspunkt ved slutten av en runde
	- Oppdatere gjenopprettingspunkt etterhvert som den lander på et 	  		  
      reparasjonspunkt eller flagg ved slutten av en fase
	- Starter med 2 skade dersom den mister et liv og må starte fra et 	  	  	   
      gjenopprettingspunkt
	- Beveger seg i henhold til programkortene som er i registerene
	- Er i stand til å flytte på andre roboter som er i veien når den beveger seg
	- Fyrer av laser (1 skade) mot nærmeste robot dersom det er en robot i skuddlinjen. Kan ikke 	   
      se/skyte gjennom vegger og kun nærmeste robot blir skadet
	- Tas ut av spill dersom den mister alle liv 

**Spiller:**

	- Valgmulighet til å starte med 4 liv istedenfor 3 dersom det er 5 eller flere spillere
	- Oversikt over liv, skade, register, kort
	- Mulighet til å slå av strømmen til roboten
	- Mulighet til å velge kort som skal legges i register
	- Mulighet til å bekrefte valget av kort i register eller å slå av strømmen til sin robot
	- (oversikt over hvilke flagg roboten har vært igjennom i riktig rekkefølge så langt)
	- (ny) Multiplayer over LAN eller Internet 
	- (ny) Mulighet til å spille mot AI styrte roboter, evt roboter som er random med tanke på handlinger

**Programkort:**

	- Blir delt ut tilfeldig før starten av en ny runde
	- 9 kort til spillere med robot med 0 skade
	- 8 kort til spillere med robot med 1 skade
	- 7 kort til spillere med robot med 2 skade
	- 6 kort til spillere med robot med 3 skade
	- 5 kort til spillere med robot med 4 skade
	- 4 kort til spillere med robot med 5 skade, register 5 låst
	- 3 kort til spillere med robot med 6 skade, register 4 og 5 låst
	- 2 kort til spillere med robot med 7 skade, register 3, 4 og 5 låst
	- 1 kort til spillere med robot med 8 skade, register 2, 3, 4 og 5 låst
	- 0 kort til spillere med robot med 9 skade, alle register låst
	- 18 kort med roter 90 grader til høyre , prioritet 80-420
	- 18 kort med roter 90 grader til venstre,  prioritet 70-410
	- 6 kort med roter 180 grader, prioritet 10-60
	- 18 kort med beveg 1 rute fremover, prioritet 490-660
	- 12 kort med beveg 2 rute fremover, prioritet 670-780
	- 6 kort med beveg 3 rute fremover, prioritet 790-840
	- 6 kort med beveg 1 rute bakover, prioritet 430-480
 	- Dersom et register blir låst uten at det befinner seg et kort i det blir neste trukket 	   
      kort automatisk lagt inn i det låste registeret
	- Samles inn ved slutten av en runde, kort i låste registre blir ikke samlet inn

**(Ny) Options kort:**
    
    - Trekker ett optionskort dersom du står på et reperasjonspunkt ved slutten av en runde 
    - Settes i spill automatisk fra det blir trukket og er synlig til andre spillere
    - Gir en bonus til robotene
    - Prioritet på optionskort bestemmes utifra docking assignment
    - Kan ofre et optionskort til å blokkere 1 skade, valget må taes da skaden blir påført
    - En robot som blir ødelagt enten ved å miste all HP eller å falle i et hull mister sitt optionskort
    - Optionskort som er blitt brukt og blitt enten ofret eller mistet legges ikke tilbake til i kortstokk
    Optionskort:
    - Superior Archive: "When reentering play after being destroyed, your robot does not recieve the normal
    2 damage tokens"
    - Reverse Gear: "Whenever you execute a Back Up, you may move your robot back 2 spaces instead of 1.
    Priority is that of the Back Up"
    - Pressor Beam: ""Whenever you could fire your main laser at a robot, you may instead fire the Pressor
    Beam. This moves the target robot 1 space away from your robot.
    

**Runde:**

	- Før første runden starter blir robotene tildelt en tilfeldig start posisjon
	- En runde starter etter at alle spillerene har valgt programkort til registerene eller 	  
      valgt å slå av strømmen til en robot og bekreftet valget
	- En runde består av 5 faser en for hvert kort i registerene
	- Programkort i registerene er låst mens runden pågår
	- Hendlesesforløpet for slutten av en runde er reparere skade, slå av strømmen på 	  
      roboter og samle inn kort. Prioritet for handlinger som ikke bestemmes av 	 	  
      programkort bestemmes av startposisjon
    - (klarifikasjon) For register så skal det være fullt før en runde kan starte


**Fase:**

	- Hendlesesforløpet for en fase er robotene flytter seg etter prioritet på 	  	 	  
	  programkortene i registerene, brett elementer gjør sine handlinger, laser aktiveres, 	  
      skade telles opp, gjenopprettingpunkt oppdateres og til slutt blir flagg registrert
	- Hendlesesforløpet for brett elementer er ekspress samlebånd, vanlig samlebånd, 	  
      dyttere og til slutt gir. Deretter følger det samme hendelsesforløpet som beskrevet 	   
      i punktet over


# Første iterasjon:
- Prosjektdokumenter/deliverables
- Plassere en brikke på brettet

# Andre iterasjon:
- UML over prosjektet
- Klasser implementert for kort og kortstokk

# Tredje iterasjon:
- Oppdatert dokumentasjon (kode og prosjekt)
- Rydde opp i mappestruktur
- Fjerne klasser som ikke er i bruk