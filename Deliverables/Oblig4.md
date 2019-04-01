# Deloppgave 1: Prosjekt og prosjektstruktur 
Innfør en testrolle. Velg en person som kan ha testrolle (det kan være den som har lead 
eller kundeansvar, eller en annen). Denne personen vil ha hovedansvar for 
testing av applikasjonen og kan delegere både oppgaver som skal implementeres/utføres, 
og ﬁnne en strategi for testing (feks hva som skal testes manuelt og hva som skal testes 
automatisk) i samarbeid med de andre. Hvis teamet bruker manuelle tester, leverer dere en 
detaljert beskrivelse av dem.
Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt 
å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere 
gjøre annerledes for å forbedre måten teamet fungerer på?
Hvordan er gruppedynamikken?
Hvordan fungerer kommunikasjonen for dere?
Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som 
kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere 
kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.
Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp 
under neste sprint.
Referat fra møter siden forrige leveranse skal legges ved. Under vurdering vil det 
vektlegges at alle bidrar til kodebasen. Under denne leveransen er det viktig at 
teammedlemmer som tidligere ikke har programmert så mye skal bidra til kodebasen. De 
som ikke har kodet noe særlig skal gjøre mer av det, mens de som kun har kodet skal 
prøve seg på feks design. Her er det lurt å samarbeide, men slik at den som har minst 
erfaring er den som sitter med maskinen og gjør selve arbeidet. Skriv kort om hvordan 
dere håndterer dette for å sikre best mulig kompetanseoverføring. Husk at den som er 
minst erfaren trenger å få bruke litt tid på å både tenke og utforske nye områder, og 
at alle har verdifulle innspill selv med lite erfaring.
Gruppeleder må ha tilgang til project board. Grupper som ikke har project 
board på github (feks Trello) må legge ved link til deres løsning. 

**Deloppgave 1**

    - Tester: Jonas
    - De som ikke har kodet så mye får jobbe med Action og Board så får mulighet til å sette
    seg in alle klassene som andre har kodet. De som har kodet mye kan prøve seg mer på
    det grafiske etterhvert.
    - Bør merge branch til master så ofte man har en stabil branch. Selv om man  
    ikke er 100% ferdig slik at det ikke blir for mye krøll
    - Gruppedynamikken har vært god, men det har ikke vært så mye aktivitet på
    discord siden møte som gjaldt forrige oblig.
    
    Prosjektretrospektiv:
    - For å hjelpe med testdekning så er tester er den eneste som kan flytte 
    issues fra testing/review over til done
    - Opprette mappestruktur for de forskjellige elementene og slette ting
    som ikke er i bruk 
    - Oppdatere dokumentasjon for kode og for prosjektet
    

#  Deloppgave 2: krav 
Her kommer resten av kravene til løsningen. Siden alle jobber i 
ulikt tempo vil det være noe ulikt hvilke krav som kan innfris, men alle bør 
kunne nå MVP i løpet av prosjektet. De som har nådd MVP kan velge å implementere 
ﬂere features, men vi vil også anbefale å bruke tid på å få det som ﬁnnes av 
funksjonalitet til å bli ordentlig bra (fjerne feil og ﬁkse detaljer).
Når dere begynner på dette redegjør for hva dere velger, og hvorfor.
Kravliste for å nå MVP:
Man må kunne spille en komplett runde
Man må kunne vinne spillet spillet ved å besøke siste ﬂagg (fullføre et spill) 
Det skal være lasere på brettet 
Det skal være hull på brettet 
Skademekanismer (spilleren får færre kort ved skade) 
Spillmekanismer for å skyte andre spillere innen rekkevidde 
med laser som peker rett frem 
fungerende samlebånd på brettet som ﬂytter robotene 
fungerende gyroer på brettet som ﬂytter robotene 
game over etter 3 tapte liv 
multiplayer over LAN eller Internet (trenger ikke gjøre noe fancy her, 
men må kunne spille på ulike maskiner mot hverandre) 
power down 
samlebånd som går i dobbelt tempo 
options-kort 
plassere ﬂagg selv før spillet starter 
sette sammen ulike brett til større spillﬂate 
spille mot AI (single-player-mode), evt spill mot random-roboter
Etter at disse kravene er ferdig implementert, kan følgende krav vurderes. 
Dette er veldig grove krav som dere må gjøre mer arbeid med å ﬁnne ut av 
for at det skal bli bra. Velg i så fall en og en funksjonalitet og 
utvikle denne til den er ferdig før dere går over på neste. 
Det er også åpent for å deﬁnere helt egne krav, men da må dere 
begrunne hvorfor dere mener det er viktigere enn de listet under, 
samt få godkjenning for å gå i en annen retning.

scoringssystem: huske hvor mange spill du har vunnet, 
evt hva de ulike plasseringene du har hatt er 
mer avansert scoringssystem: antall trekk for å nå ﬂagg? 
Kanskje ha en spillmodus som er på noen få typer brett med fastsatte ﬂagg 
der poenget er å komme seg fortest mulig frem? 
ﬂere rutetyper på brettet (feks teleporter, skubbe-dingser som 
fyrer av på ulike faser) 
lage egne brett 
generere brett 
lagre spill underveis slik at det går an å avslutte og 
sette igang spill igjen senere 
spille over Internet, og ikke bare LAN 
ﬁrst-player-view av når roboten beveger seg (evt zoomet inn slik at det 
blir mer “video-følelse” av det hele) 
“replay” av viktige øyeblikk som å vandre i døden/vinne spillet osv. 
enklere variant av replay: vise hvor roboten var og hvordan den endte 
opp slik den er etter alle fasene er over (evt vise dette for alle spillere på brettet) 
gi roboter litt mer “personlighet”, slik at alle roboter har pros/cons som 
gjør at det betyr litt mer hvilken du velger i spillet 
Alle robotene kan ha et option-kort by default. Eller en tradeoff 
der man må ta vekk noe for at optionskortet skal virke? Feks: 
random bevegelseskort kan ikke deles ut, som feks snu mot høyre eller 
move 3? Her er det mange muligheter til å variere. 
Dersom dere velger denne må dere gjøre godt arbeid for å 
sikre spillmekanikken og at det blir rettferdig uavhengig av 
hvilken robot man velger. Dere kan også velge å lage egne stats 
(positive eller negative) på robotene uavhengig av options-kortene. 

# Deloppgave 3: kode 
Dere må dokumentere hvordan prosjektet bygger, testes 
og kjøres, slik at det er lett å teste koden. 
Under vurdering kommer koden også til å brukertestes.
Produktet skal fungerer på Linux ( Ubuntu / Debian ), Windows og OSX.
Dokumentér også hvordan testene skal kjøres. 
Kodekvalitet og testdekning vektlegges. 
Merk at testene dere skriver skal brukes i produktet. 