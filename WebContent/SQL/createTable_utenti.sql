DROP TABLE users if exist;
CREATE TABLE users IF NOT EXIST (
         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
         FirstName  VARCHAR(100), 
         LastName VARCHAR(100), 
         username  VARCHAR(100),
         password  VARCHAR(100)
       );

-- Popolazioni 
 DROP TABLE popolazione ;
 CREATE TABLE popolazione(
 	id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY, 	
 	descrizione VARCHAR(100)
 );
 
-- Un utente a quale popolazione appartiente
 DROP TABLE appartenenza ;
 CREATE TABLE appartenenza(
 	idUtente INT,
 	idPopolazione INT,
 	FOREIGN KEY idUtente REFERENCES utente(id),
 	FOREIGN KEY idPopolazione REFERENCES popolazione(id) 	
 );

-- Gestori di Assemblea
DROP TABLE gdA ;
CREATE TABLE gdA (	
	dotName VARCHAR(32), -- nome in formato .1.2.3.
	ip VARCHAR(16), -- indirizzo IP
	votanti INT, -- numero di utenti votanti contenuti nel gdA (la somma dei figli e' il valore del padre)
	PRIMARY KEY (dotName,ip)	
);

-- Proposte/richieste di legge 
DROP TABLE proposte ;
CREATE TABLE  proposte (
	   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	   voti_si int default(0),
	   voti_no int default(0),
	   voti_ast int default(0),
	   votanti int not null,
	   level int default(0),
	   -- gestore dell'assemblea owner di quella proposta
	   gdA_owner varchar(100),
	   descrizione varchar(100)
);

-- Qui si conteggiano le motivazioni per cui gli utenti dicono "si/no/astenuto"
DROP TABLE motivazioni;
CREATE TABLE motivazioni(
	id INT,
	idProposta INT,
	-- In merito alla proposta "idProposta" questa motivazione che fa'?	
	azioneProposta ENUM ('APPOGGIA','BOCCIA','ASTIENE'),
	-- Identifica il numero di votanti che sono concordi a tale motivazione. 
	concordi INT,
	descrizione VARCHAR(100)
	FOREIGN KEY idProposta REFERENCES proposte(id)
);

-- Votazioni richieste ad un "gestore di assemblea".
DROP TABLE votazioni_richieste;
CREATE TABLE  votazioni_richieste(
	idGdA int ,
	idProposta int,
	foreign key (idGdA) references gestore_di_assemblea(id),
	foreign key (idProposta) references proposte(id)
);

-- Registra le votazioni effettuate, non il "si/no/astenuto"
DROP TABLE votazioni_effettuate;
CREATE TABLE  votazioni_effettuate(
	idUtente INT,
	idProposta INT,
	idGDA INT,
	idRichiesta INT,
	PRIMARY KEY(idUtente,idProposta),
	FOREIGN KEY (idProposta) REFERENCES proposte(id),
	FOREIGN KEY (idUtente) REFERENCES utente(id),
	FOREIGN KEY (idRichiesta) REFERENCES votazioni_richieste(id)
);


