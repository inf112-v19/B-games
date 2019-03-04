# Deloppgave 1: Prosjekt og prosjektstruktur 
Hvordan funker rollene i teamet? 
Trenger dere å oppdatere hvem som er teamlead eller kundekontakt? 
Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene 
faktisk innebærer for dere. Er det noen erfaringer enten team-messig eller 
mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene 
dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å 
forbedre måten teamet fungerer på? Hvordan er gruppedynamikken? Hvordan 
fungerer kommunikasjonen for dere? Gjør et kort retrospektiv hvor dere 
vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal 
handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, 
men dette handler ikke om feilretting, men om hvordan man jobber og 
kommuniserer. Under vurdering vil det vektlegges at alle bidrar til 
kodebasen. Hvis det er stor forskjell i hvem som committer, må dere 
legge ved en kort forklaring for hvorfor det er sånn. husk å committe alt. 
(Også designﬁler) Referat fra møter siden forrige leveranse skal legges ved. 
Sett opp 4-5 punkter som skal følges opp.

**Deloppgave 1**

    Er noe usikkerhet rundt hva rollene innebærer. Har ikke vært noe særlig å gjøre for 
    kundekontakt. For projectlead har det mest vært å opprette issues på Projectboardet
    og sørge for at alle har en issue assigned. En erfaring som er gjort er at vi
    bør bruke branches når vi skriver kode før vi pusher til master. Det at en person 
    er ekstern er en ekstra utfordring, så mer av kommunikasjonen som blir gjort på møtene på 
    tirsdag bør gjøres over Discord. En utfordring er at det kan bli uoversiktlig 
    over hva andre driver med og at ingen sitter med det hele bildet. For å
    imøtekomme dette bør man refaktorere koden en har skrevet etter at den passerer testene
    for å gjøre den lettere å lese for andre som skal bruke den videre.
    Til nå har vi blitt enige om prosjektspesifikasjon om hvordan programmet skal 
    oppføre seg, UML over hvordan programmet er strukturert til nå og har skrevet noen 
    av de mindre klassene. En ting som vi oppdaget er at det er vanskelig å 
    starte på de større klassene før en har en god forståelse av hvordan de mindre 
    klassene fungerer. 

    1. Bruk av branches for kode en skriver, slik at de kan gjennomgåes av andre
    før det pushes til master
    2. Legg igjen en beskjed på discord ved større oppdateringer med en kort beskrivelse
    av hva som er gjort
    3. Bruk av kommentarer/tydlige variabel/metode navn for å gjøre det enklere for 
    andre å sette seg inn i hva du har gjort
    4. Bedre og tydligere comitt meldinger


# Deloppgave 2: krav 
Presisering av krav som har kommet fra kunden. Hva blir de faktiske oppgavene? 
Teamets prioritering av oppgavene Hvis det er gjort endringer i rekkefølge utfra hva 
som er gitt fra kunde, hvorfor er dette gjort? Hvordan vil dere veriﬁsere at kravene er 
oppfylt? (Hva er akseptansekriteriene?) Oppdatere hvilke krav dere har prioritert, hvor 
langt dere har kommet og hva dere har gjort siden forrige gang Kravlista er lang, men det 
er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere 
at de oppgavene som er utført holder høy kvalitet. Jamf deloppgave 4: Utførte oppgaver skal 
være ferdige. 


**Deloppgave 2**
 
    - Akseptansekriteriene for spill-logikken er at klassene oppfører seg
    som beskrevet i spesifikasjonen. 
    - Krav vi har prioritert er å lage UML, prosjektspesifikasjon,
    implementere Action klasse som styrer spillet,
    brett (med gui), Actor(robot), kort, kortstokk og et par Tiles som brettet består av
    EmptyTile, LaserTile, ConveyorBelt. 
    - Utførte oppgaver siden forrige gang er UML og 
    logikk for kort og kortstokk. 
    - Prosjektspesifikasjon og UML må holdes oppdatert gjennom prosjektet.**

# Deloppgave 4: kode 
Dere må dokumentere hvordan prosjektet bygger, 
testes og kjøres, slik at det er lett å teste koden. 
Under vurdering kommer koden også til å brukertestes. 
Dokumentér også hvordan testene skal kjøres. Kodekvalitet og testdekning vektlegges. 
Merk at testene dere skriver skal brukes i produktet. Utførte oppgaver skal være ferdige. 
Lever klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.)

**Deloppgave 4**

    - Programming language: Java
    - Software Development Kit(SDK): Java Development Kit 1.8
    - Source Encoding: UTF-8
    - Game Development Framework: libGDX 1.9.9 
    - Test for game logic: JUnit 4.11
    - Test for game graphic: not yet defined
    - Project Management Tool: Maven Project Object Model 4.0.0
    - Source Folder: src/main/java 
    - Test Source Folder: src/test/java 
    - Resource Folder: assets
    - Excluded Folder: target
