# Deloppgave 1: Prosjekt og prosjektstruktur 
Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å 
nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere 
gjøre annerledes for å forbedre måten teamet fungerer på?
Hvordan er gruppedynamikken?
Hvordan fungerer kommunikasjonen for dere?
Referat fra møter siden forrige leveranse skal legges ved. 
Under denne leveransen skal teamet rotere litt på opgavene, 
slik at medlemmer som har kodet masse tar del i design-arbeid, 
utforming/tydeliggjøring/forbedring av krav, osv.
Gruppeleder må ha tilgang til project board. Grupper som ikke har 
project board på github (feks Trello) må legge ved link til deres løsning.
Utfør et retrospektiv før leveranse med fokus på hele prosjektet:
Hva justerte dere underveis, og hvorfor? Ble det bedre? hva har fungert best, 
og hvorfor? (Hva er dere mest fornøyde med?) hvis dere skulle fortsatt med prosjektet, 
hva ville dere justert? hva er det viktigste dere har lært? 

**Deloppgave 1**

    .


# Deloppgave 2: krav 
Leveransen kommer til å vurderes på kompletthet av implementerte krav, 
samt at applikasjonen vil testes. Legg derfor ved liste over alle krav dere har ferdigstilt.

Liste over krav (som beskrevet i oblig 4): Minstekrav for produkt (MVP)
Man må kunne spille en komplett runde 
Man må kunne vinne spillet spillet ved å besøke siste ﬂagg (fullføre et spill) 
Det skal være lasere på brettet 
Det skal være hull på brettet 
Skademekanismer (spilleren får færre kort ved skade) 
Spillmekanismer for å skyte andre spillere innen rekkevidde med laser som peker rett frem 
fungerende samlebånd på brettet som ﬂytter robotene 
fungerende gyroer på brettet som ﬂytter robotene 
game over etter 3 tapte liv 
multiplayer over LAN eller Internet (trenger ikke gjøre noe fancy her, men må kunne spille på ulike maskiner mot hverandre) 
Feilhåndtering ved disconnect. (Spillet skal i hvertfall ikke kræsje) 
power down
samlebånd som går i dobbelt tempo 
spille mot AI (single-player-mode), evt spill mot random-roboter

Etter at disse kravene er ferdig implementert, kan følgende krav vurderes. 
Dette er veldig grove krav som dere må gjøre mer arbeid med å ﬁnne ut av for at 
det skal bli bra. Velg i så fall en og en funksjonalitet og utvikle denne til 
den er ferdig før dere går over på neste. Det er også åpent for å deﬁnere helt 
egne krav, men da må dere begrunne hvorfor dere mener det er viktigere enn de listet 
under, samt få godkjenning for å gå i en annen retning. Valgfrie krav som kan 
implementeres etter ønske (Nice to have)

options-kort 
plassere ﬂagg selv før spillet starter 
sette sammen ulike brett til større spillﬂate 
scoringssystem: huske hvor mange spill du har vunnet, evt hva de ulike plasseringene du har hatt er 
mer avansert scoringssystem: antall trekk for å nå ﬂagg? Kanskje ha en spillmodus som er på noen få typer brett med fastsatte ﬂagg der poenget er å komme seg fortest mulig frem? 
ﬂere rutetyper på brettet (feks teleporter, skubbe-dingser som fyrer av på ulike faser) 
lage egne brett '
generere brett 
lagre spill underveis slik at det går an å avslutte og sette igang spill igjen senere 
spille over Internet, og ikke bare LAN 
ﬁrst-player-view av når roboten beveger seg (evt zoomet inn slik at det blir mer “video-følelse” av det hele) 
“replay” av viktige øyeblikk som å vandre i døden/vinne spillet osv. 
enklere variant av replay: vise hvor roboten var og hvordan den endte opp slik den er etter alle fasene er over (evt vise dette for alle spillere på brettet) 
gi roboter litt mer “personlighet”, slik at alle roboter har pros/cons som gjør at det betyr litt mer hvilken du velger i spillet 
Alle robotene kan ha et option-kort by default. Eller en tradeoff der man må ta vekk noe for at optionskortet skal virke? Feks: random bevegelseskort kan ikke deles ut, som feks snu mot høyre eller move 3? Her er det mange muligheter til å variere. Dersom dere velger denne må dere gjøre godt arbeid for å sikre spillmekanikken og at det blir rettferdig uavhengig av hvilken robot man velger. Dere kan også velge å lage egne stats (positive eller negative) på robotene uavhengig av options-kortene. 

**Deloppgave 2**

    .

# Deloppgave 3: kode 
Som en del av denne leveransen skal dere legge ved en liste (og kort beskrivelse?) over kjente feil og svakheter i produktet.
Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett å teste koden. Under vurdering kommer koden også til å brukertestes.
Produktet skal fungerer på Linux ( Ubuntu / Debian ), Windows og OSX. 
Dokumentér også hvordan testene skal kjøres. 
Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet.
Legg også ved et klassediagram som viser de viktige delene av koden. Tilpass klassediagrammet slik at det gir leseren mest mulig informasjon (feks Intellij kan tilpasse klassediagram som genereres). Hvis dere
gjør justeringer som feks å ta vekk ubetydelige klasser, skriv noen linjer om hvordan dere har tilpasset klassediagrammet). Det er viktig at klassediagrammet viser hovedelementene i programmet og hvordan disse kommuniserer. Tenk at dere skal forklare arkitekturen i programmet deres til en ny utvikler. 

**Deloppgave 3**

    .
    

# Deloppgave 4: Presentasjon
Presentasjonene vil avholdes uken etter leveransen (7. og 9. mai), og varigheten skal være på 5 minutter

Presentasjonen må inkludere følgende:
Oppsummering av det viktigste dere har lært i løpet av prosjektet både teknisk, organisatorisk og kommunikasjonsmessig. 
Demo av prosjektet 
Hvis dere skulle gjort en ting annerledes, hva og hvorfor? 
Hvor ville dere gått videre med prosjektet? 