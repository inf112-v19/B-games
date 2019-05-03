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
    - Hull, roboter mister ett liv dersom de faller i et hull på brettet
    - (Optional) Spiller(e) (player 1?) skal ha mulighet til å plassere flagg selv på brettet
    - (Optional) Flere brett skal kunne settes sammen til et større brett
    - (Optional) ﬂere rutetyper på brettet (feks teleporter, skubbe-dingser som fyrer av på 
        ulike faser) 
    - (Optional) lage egne brett
    - (Optional) generere brett
    - (Optional) lagre spill underveis slik at det går an å avslutte og sette igang spill 
        igjen senere

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
	- Game Over dersom roboten mister alle liv
	- (Optional) ﬁrst-player-view av når roboten beveger seg (evt zoomet inn slik at det blir mer 
	  “video-følelse” av det hele)
	- (Optional) “replay” av viktige øyeblikk som å vandre i døden/vinne spillet osv.
	- (Optional) enklere variant av replay: vise hvor roboten var og hvordan den endte opp slik den 
	    er etter alle fasene er over (evt vise dette for alle spillere på brettet)
	- (Optional) gi roboter litt mer “personlighet”, slik at alle roboter har pros/cons som gjør 
	    at det betyr litt mer hvilken du velger i spillet 
	- (Optional) Alle robotene kan ha et option-kort by default. Eller en tradeoff der man må ta vekk 
	    noe for at optionskortet skal virke? Feks: random bevegelseskort kan ikke deles ut, som 
	    feks snu mot høyre eller move 3? Her er det mange muligheter til å variere. 
	    Dersom dere velger denne må dere gjøre godt arbeid for å sikre spillmekanikken og at 
	    det blir rettferdig uavhengig av hvilken robot man velger. Dere kan også velge å lage 
	    egne stats (positive eller negative) på robotene uavhengig av options-kortene. 

**Spiller:**

	- Valgmulighet til å starte med 4 liv istedenfor 3 dersom det er 5 eller flere spillere
	- Oversikt over liv, skade, register, kort
	- Mulighet til å slå av strømmen til roboten(Power Down)
	- Mulighet til å velge kort som skal legges i register
	- Mulighet til å bekrefte valget av kort i register eller å slå av strømmen til sin robot
	- (oversikt over hvilke flagg roboten har vært igjennom i riktig rekkefølge så langt)
	- Multiplayer over LAN eller Internet 
	- Mulighet til å spille mot AI styrte roboter, evt roboter som er random med tanke på handlinger
	- Feilhåndtering derson en spiller blir disconnected fra spillet
	- (Optional) scoringssystem: huske hvor mange spill du har vunnet, evt hva de ulike plasseringene 
	    du har hatt er
	- (Optional) mer avansert scoringssystem: antall trekk for å nå ﬂagg? Kanskje ha en spillmodus som er 
	    på noen få typer brett med fastsatte ﬂagg der poenget er å komme seg fortest mulig frem?
	- (Optional) spille over Internet, og ikke bare LAN

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

**(Optional) Options kort:** 
    
    - Trekker ett optionskort dersom du står på et reperasjonspunkt ved slutten av en runde 
    - Settes i spill automatisk fra det blir trukket og er synlig til andre spillere
    - Gir en bonus til robotene
    - Prioritet på optionskort bestemmes utifra docking assignment
    - Kan ofre et optionskort til å blokkere 1 skade, valget må taes da skaden blir påført
    - En robot som blir ødelagt enten ved å miste all HP eller å falle i et hull mister sitt 
        optionskort
    - Optionskort som er blitt brukt og blitt enten ofret eller mistet legges ikke tilbake til 
        i kortstokk
    
    Optionskort:
    - Superior Archive: "When reentering play after being destroyed, your robot does not recieve 
        the normal 2 damage tokens"
    - Reverse Gear: "Whenever you execute a Back Up, you may move your robot back 2 spaces instead 
        of 1. Priority is that of the Back Up"
    - Pressor Beam: "Whenever you could fire your main laser at a robot, you may instead fire the 
        Pressor Beam. This moves the target robot 1 space away from your robot."
    - Crab Legs: "When programming your registers, you may put a Move 1 card in the
        same register as a Rotate Left or Rotate Right card. If you do, during that register
        your robot will move 1 space to the left or right, respectively, without rotating. Priority
        is that of the Move 1."
    - Double-Barreled Laser: Whenever your robot fires its main laser, it fires two shots instead of one. 
        You may use this Option with Fire Control and/or High-Power Laser.
    - Ablative Coat: Ablative Coat absorbs the next 3 Damage your robot recieves. 
        Put those Damage tokens onto this card instead of onto your Program Sheet. 
        Discard this card and the tokens when you put the third one on.
    - High-Power Laser: Your robot's main laser can shoot through one wall or robot to get to a 
        target robot. If you shoot through a robot, that robot also recieves full damage. You 
        may use this Option with Fire Control and/or Double-Barreled Laser.
    - Recompile: Once each turn, you may discard the hand of Program cards dealt to you and draw a 
        new hand from the deack. Your robot recieves 1 Damage token.
    - Fire Control: Whenever your robot hits another robot with its main laser, instead of doing 
        damage you may choose one of the target robot's registers and lock it or choose one of that 
        player's Options and destroy it. (The player can't discard an Option to avoid this effect.)
    - Gyroscopic Stabilizer: Before players reveal the cards in their first registers each turn, 
        state whether this Option is active. When it is, your robot isn't rotated by gears or 
        rotating conveyor belts for that entire turn.
    Mini Howitzer: Whenever you could fire your main laser at a robot, you may fire the Mini Howitzer 
        instead. This pushes the target robot 1 space away from your robot, and the target 
        robot recieves 1 Damage token. (Robots can't be pushed through walls.) You may use 
        this Option five times. Put a Damage token on this card each tmie you use it and 
        discard this card and the tokens when you put the fifth one on.
    - Radio Control: Whenever you could fire your main laser at a robot, you may instead fire the 
        Radio Control beam. This causes the target robot to execute your robot's program for the 
        rest of the turn. In cases of card priority, the target robot moves immediately after your robot.
    - Extra Memory: You recieve one extra Program card each turn. (You still discard all unused 
        Program cards when you're done programming your registers.)
    - Flywheel: After all players are done programming their registers each turn, you may put one of 
        your remaining Program cards face down onto this card. You can add that Program card to those 
        dealt to you on any subsequent turn. You can have only one card on Flywheel at a time.
    Power-Down Shield: As long as your robot is powered down, each register phase can prevent up to 
        1 Damage to it from each of the four directions.
    Brakes: Whenever you execute a Move 1, you may move your robot 0 spaces instead of 1. Priority is 
        that of the Move 1.
    Abort Switch: Once each turn, you may replace one of the Program cards you reveal with the top 
        card from the deck. If you do, you must replace the Program cards for all your remaining registers 
        the same way that turn.
    Circuit Breaker: If you have 3 or more Damage tokens on your Program Sheet at the end of your turn, 
        your robot will begin the next turn powered down.
    Rear-Firing Laser: Your robot has a rear-firing laser in addition to its main laser. This laser follows 
        all the same rules as the main laser.
    Tractor Beam: Whenever you could fire main laser at a robot that isn't in a adjacent space, you may 
        instead fire the Tractor Beam. This pulls the target robot 1 space toward your robot.
    Ramming Gear: Whenever your robot pushes or bumps into another robot that robot recives 1 Damage token.
    Conditional Program: After you program your registers each turn, you may put one of the Program 
        cards left in your hand face down onto tgis Option instead of discarding it. 
        Later that turn, you can substitute that card for one you had programmed in any 
        register, discarding the original card. Annonuce the change before anyone reveals 
        Program cards for that register. If you put a card on this Option and dont use it, 
        discard it at the end of the turn.
    Fourth Gear: Whenever you execute a Move 3, you may move your robot 4 spaces instead of 3. 
        Priority is that of the Move 3.
    Dual Processor: When programming your registers, you may put both a Move card (Move 1, 
        Move 2, Move 3, or Back Up) and a Rotate card (Rotate Left, Rotate Right, or U-Turn) 
        in the same register. If you do, during that phase your robot will move 1 space 
        less than the Move card says to move and then execute the Rotate card. If the 
        Rotate card is a U-Turn, move 2 spaces less than the Move card says if possible.
    Mechanical Arm: Your robot can tuch a flag or repair site from 1 space away (diagonally 
        or orthogonally), as long as there isn't a wall between it and the flag or repair site.
    Scrambler: Whenever you could fire your main laser at a robot, you may instead fire the 
        Scrambler. This replaces the target robot's next programmed card with the top 
        Program card from the deck. You can't use this Option on the fifth register phase.


**Runde:**

	- Før første runden starter blir robotene tildelt en tilfeldig start posisjon
	- En runde starter etter at alle spillerene har valgt programkort til registerene eller 	  
      valgt å slå av strømmen til en robot og bekreftet valget
	- En runde består av 5 faser en for hvert kort i registerene
	- Programkort i registerene er låst mens runden pågår
	- Hendlesesforløpet for slutten av en runde er reparere skade, slå av strømmen på 	  
      roboter og samle inn kort. Prioritet for handlinger som ikke bestemmes av 	 	  
      programkort bestemmes av startposisjon
    - For register så skal det være fullt før en runde kan starte
    


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

# Fjerde iterasjon:
