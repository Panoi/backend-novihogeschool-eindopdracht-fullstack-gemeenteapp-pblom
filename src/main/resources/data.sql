INSERT INTO municipality (name, province)
VALUES ('Amsterdam', 'NOORD_HOLLAND'),
       ('Haarlem', 'NOORD_HOLLAND'),
       ('Alkmaar', 'NOORD_HOLLAND'),
       ('Zaanstad', 'NOORD_HOLLAND'),
       ('Hilversum', 'NOORD_HOLLAND'),
       ('Amstelveen', 'NOORD_HOLLAND'),
       ('Hoorn', 'NOORD_HOLLAND'),
       ('Rotterdam', 'ZUID_HOLLAND'),
       ('Den Haag', 'ZUID_HOLLAND'),
       ('Utrecht', 'UTRECHT'),
       ('Amersfoort', 'UTRECHT'),
       ('Ede', 'GELDERLAND'),
       ('Nijmegen', 'GELDERLAND'),
       ('Maastricht', 'LIMBURG'),
       ('Sittard-Geleen', 'LIMBURG'),
       ('Zwolle', 'OVERIJSSEL'),
       ('Enschede', 'OVERIJSSEL'),
       ('Groningen', 'GRONINGEN'),
       ('Haren', 'GRONINGEN'),
       ('Leeuwarden', 'FRIESLAND'),
       ('Drachten', 'FRIESLAND'),
       ('Assen', 'DRENTHE'),
       ('Emmen', 'DRENTHE'),
       ('Almere', 'FLEVOLAND'),
       ('Lelystad', 'FLEVOLAND'),
       ('Middelburg', 'ZEELAND'),
       ('Goes', 'ZEELAND'),
       ('Eindhoven', 'NOORD_BRABANT'),
       ('Tilburg', 'NOORD_BRABANT')
ON CONFLICT (name) DO NOTHING;

INSERT INTO users (email, password, first_name, last_name, account_type, municipality_id, city, province)
VALUES ('admin@gemeenteapp.nl', '$2a$12$mbJ3vtgW.rS5yRmUnpBDTOq7w5PRtLBr7RAe9OrV72Yrp0oHqeBFm', 'Test', 'Admin',
        'ADMIN', 1, 'Amsterdam', 'NOORD_HOLLAND'),
       ('gemeente@gemeenteapp.nl', '$2a$12$DxDIxpLmBY1TmFWtRpOLaeHrRcu8x3lD8ldXZyiTlvX5lnrGBC42.', 'Test', 'Gemeente',
        'MUNICIPALITY', 1, 'Amsterdam', 'NOORD_HOLLAND'),
       ('inwoner@gemeenteapp.nl', '$2a$12$VsOmdEu0UPPezTPduJRdHOcRIuf1jgU6ViSXg/3zroyLYn8qdtG8S', 'Test', 'Inwoner',
        'RESIDENT', 1, 'Amsterdam', 'NOORD_HOLLAND'),
       ('inwoner2@gemeenteapp.nl', '$2a$12$B9fHwD0YodrKbuqeutqW7u5MVxZB3COXh014iBozG5ObTDwJEwhHS', 'Testtwee',
        'Inwonertwee', 'RESIDENT', 1, 'Amsterdam', 'NOORD_HOLLAND') ON CONFLICT DO NOTHING;

INSERT INTO authorities (email, authority)
VALUES ('admin@gemeenteapp.nl', 'ROLE_ADMIN'),
       ('gemeente@gemeenteapp.nl', 'ROLE_MUNICIPALITY'),
       ('inwoner@gemeenteapp.nl', 'ROLE_RESIDENT'),
       ('inwoner2@gemeenteapp.nl', 'ROLE_RESIDENT')
    ON CONFLICT DO NOTHING;

INSERT INTO proposal_photo (file_name)
VALUES
    ('kapotte speeltuin.jpg'),
    ('kapotte glijbaan.jpeg'),
    ('gevaarlijk oversteekpunt.jpg'),
    ('omgevallen verkeerslicht.jpg'),
    ('vuilnis op straat.jpg')
    ON CONFLICT (file_name) DO NOTHING;

INSERT INTO proposal (description, title, submitted_at, user_id, municipality_id, photo_file_name)
VALUES
    ('De speeltuin aan het Kastanjeplein is al maanden in slechte staat. Diverse speeltoestellen zijn kapot of gevaarlijk versleten: een schommel hangt scheef met een gebroken ketting, het klimrek mist enkele sporten en de glijbaan heeft scherpe roestplekken. De rubberen tegels in de ondergrond zijn verzakt, waardoor kinderen struikelen. Ouders hebben herhaaldelijk melding gemaakt bij de gemeente, maar tot op heden is er geen actie ondernomen. Hierdoor mijden veel ouders de speeltuin uit angst voor ongelukken, wat ten koste gaat van de speelruimte voor kinderen en het sociale contact in de wijk. Deze situatie is zorgwekkend en vraagt om directe aanpak. We pleiten voor een grondige renovatie met veilige, moderne toestellen, vergroening van het terrein en duidelijke keuring op veiligheid, zodat kinderen weer veilig kunnen spelen en de buurt opnieuw een levendige ontmoetingsplek krijgt.',
    'Herstel kapotte speeltuin', '2025-06-30 10:00:00', 3, 1, 'kapotte speeltuin.jpg'),
    ('Op het schoolplein van basisschool De Regenboog staat al weken een zwaar beschadigde glijbaan. De zijkant van het kunststof is gescheurd, wat scherpe randen veroorzaakt die kinderen kunnen verwonden. Daarnaast is de trap instabiel doordat een van de treden ontbreekt, en de bevestiging aan het frame lijkt los te zitten. Meerdere ouders hebben hun zorgen geuit bij de schoolleiding en de gemeente, maar er is nog geen actie ondernomen. Het toestel is niet afgezet, waardoor jonge kinderen het blijven gebruiken met alle risico’s van dien. Dit voorstel pleit voor directe verwijdering van het gevaarlijke toestel en de plaatsing van een nieuwe, veilige glijbaan. Ook stellen we voor om speeltoestellen voortaan jaarlijks te keuren en het onderhoud structureel op te nemen in het gemeentelijke beheer, zodat deze situatie zich niet opnieuw voordoet.',
     'Kapotte glijbaan vervangen','2025-06-30 11:00:00', 3, 1, 'kapotte glijbaan.jpeg'),
    ('Op de kruising van de Bernhardlaan en de Julianastraat ontbreekt een veilige oversteekplaats, terwijl dit punt dagelijks door tientallen voetgangers, scholieren en fietsers wordt gebruikt. Automobilisten rijden er vaak te hard en hebben slecht zicht op overstekend verkeer. De situatie is extra gevaarlijk tijdens de ochtend- en middagspits, wanneer kinderen onderweg zijn van en naar school. Eerdere verzoeken van buurtbewoners zijn genegeerd en pas recent is er een melding gedaan na een bijna-aanrijding. De foto toont de onduidelijke situatie waarin geen zebrapad, geen verkeerslicht en geen waarschuwingsbord aanwezig is. Met dit voorstel vragen wij om een verhoogd en duidelijk gemarkeerd zebrapad, extra belijning op de weg en verlichting op het kruispunt. Zo zorgen we ervoor dat voetgangers veilig kunnen oversteken en voorkomen we ongelukken op deze drukke locatie.',
     'Veilige oversteek bij drukke weg','2025-06-30 11:00:00', 3, 1, 'gevaarlijk oversteekpunt.jpg'),
    ('Tijdens een zware storm is het verkeerslicht op de kruising van de Jan Evertsenstraat en de Postjesweg omgevallen. Sindsdien functioneren de signalen niet meer, wat heeft geleid tot chaos voor zowel automobilisten als fietsers en voetgangers. De situatie is vooral gevaarlijk op drukke momenten zoals schooltijd of het einde van de werkdag. Er zijn al meerdere bijna-ongelukken gemeld, en buurtbewoners maken zich zorgen over de veiligheid. Tot op heden is er enkel een tijdelijk verkeersbord geplaatst, maar dit biedt onvoldoende duidelijkheid. Met dit voorstel vragen wij om spoedig herstel van het verkeerslicht, inclusief een stevigere fundering zodat herhaling voorkomen wordt. Daarnaast pleiten we voor periodieke inspecties van verkeersinstallaties binnen de gemeente, zodat problemen vroegtijdig gesignaleerd en aangepakt kunnen worden voordat ze een risico vormen voor de openbare veiligheid.',
     'Omgevallen verkeerslicht herstellen','2025-06-30 11:00:00', 4, 1, 'omgevallen verkeerslicht.jpg'),
    ('In de drukbezochte winkelstraat rondom het Mercatorplein stapelt het afval zich dagelijks op. Overvolle prullenbakken, verkeerd aangeboden vuilniszakken en illegaal gestort huisvuil zorgen voor een rommelig straatbeeld. Vooral in de zomer leidt dit tot stankoverlast, ongedierte en frustratie bij zowel bewoners als winkeliers. De foto toont een hoekpand waar vuilnis zich ophoopt, ondanks herhaalde meldingen bij de gemeente. Er lijkt geen controle of handhaving plaats te vinden en afval wordt soms dagenlang niet opgehaald. Dit voorstel pleit voor het plaatsen van grotere en afsluitbare afvalbakken, een verdubbeling van het aantal ophaalmomenten en handhaving met boetes bij fout aangeboden afval. Daarnaast stellen we voor om voorlichtingscampagnes op te zetten over afvalscheiding en juiste aanbiedingsregels. Zo maken we samen de straat weer leefbaar, schoon en aantrekkelijk voor bewoners én bezoekers.',
     'Aanpak zwerfafval in winkelstraat','2025-06-30 11:00:00', 4, 1, 'vuilnis op straat.jpg')
    ON CONFLICT DO NOTHING;

INSERT INTO status (municipality_id, proposal_id, status)
VALUES
    (1, 1, 'INPROGRESS'),
    (1, 2, 'APPROVED'),
    (1, 3, 'DENIED'),
    (1, 4, 'INPROGRESS'),
    (1, 5, 'PENDING')
    ON CONFLICT DO NOTHING;

INSERT INTO review (message, submitted_at, user_id, proposal_id)
VALUES
    ('Speeltuin is al veelste lang een ramp!', '2025-07-01 12:00:00', 4, 1),
    ('Wij hebben een aantekening gemaakt en gaan ermee aan de slag.', '2025-08-01 16:00:00', 2, 1),
    ('Hoogtijd voor een nieuwe!.', '2025-07-01 12:10:00', 4, 2),
    ('Het zien er inderdaad niet meer uit, wij hebben akkoord voor een nieuwe', '2025-10-01 13:10:00', 2, 2),
    ('Ik steek daar vaak over, en het is echt gevaarlijk. Tijd voor verandering.', '2025-07-01 12:20:00', 4, 3),
    ('Helaas kunnen wij hier niets aan doen, excuus voor het ongemak.', '2025-09-01 13:20:00', 2, 3),
    ('Dat verkeerslicht is echt een ramp nu. Elke dag chaos daar.', '2025-07-01 12:30:00', 3, 4),
    ('Dit mag inderdaad niet gebeuren, wij zijn ermee bezig.', '2025-07-01 13:30:00', 2, 4),
    ('De hoeveelheid vuilnis is echt niet normaal soms. Dit is nodig.', '2025-07-01 12:40:00', 3, 5)
    ON CONFLICT DO NOTHING;