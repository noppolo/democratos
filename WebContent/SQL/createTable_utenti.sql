DROP TABLE IF EXISTS votazioni_effettuate;
DROP TABLE if exists votazioni_richieste ;
DROP TABLE if exists motivazione ;
DROP TABLE if exists proposta ;
DROP TABLE if exists gdA ;
DROP TABLE if exists appartenenza ;
DROP TABLE IF EXISTS utente;
DROP TABLE if exists popolazione ;

-- Popolazioni 
 CREATE TABLE IF NOT EXISTS popolazione (
 	id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY, 	
 	descrizione VARCHAR(100)
 );
 INSERT INTO popolazione(descrizione) VALUES ('condominio');
 INSERT INTO popolazione(descrizione) VALUES ('italiana');
 INSERT INTO popolazione(descrizione) VALUES ('roma');
 
 --
 
CREATE TABLE IF NOT EXISTS utente (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	FirstName  VARCHAR(100),
	LastName VARCHAR(100),
	username  VARCHAR(100),
	password  VARCHAR(100),
	idPopolazionePartenza INT,
	FOREIGN KEY (idPopolazionePartenza) REFERENCES popolazione(id)
	);
INSERT INTO utente(FirstName,LastName,username,password,idPopolazionePartenza) VALUES('N','R','nicola','aaa',1);
       
-- Un utente a quale popolazione appartiente
 CREATE TABLE IF NOT EXISTS appartenenza(
 	idUtente INT,
 	idPopolazione INT,
 	FOREIGN KEY (idUtente) REFERENCES utente(id),
 	FOREIGN KEY (idPopolazione) REFERENCES popolazione(id)
 	ON DELETE CASCADE
 );
INSERT INTO appartenenza(idUtente,idPopolazione) VALUES (1,1);
INSERT INTO appartenenza(idUtente,idPopolazione) VALUES (1,3);
 
-- Gestori di Assemblea
CREATE TABLE IF NOT EXISTS gdA (	
	dotName VARCHAR(32), -- nome in formato .1.2.3.
	ip VARCHAR(16), -- indirizzo IP
	votanti INT, -- numero di utenti votanti contenuti nel gdA (la somma dei figli e' il valore del padre)
	PRIMARY KEY (dotName)
);

-- Proposte/richieste di legge 
CREATE TABLE IF NOT EXISTS proposta (	
	   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	   voti_si int default 0,
	   voti_no int default 0,
	   voti_ast int default 0,
	   votanti int not null,
	   level int default 0,
	   gdA_owner varchar(32) not null,
	   descrizione varchar(100)
);

-- Qui si conteggiano le motivazioni per cui gli utenti dicono "si/no/astenuto"
CREATE TABLE IF NOT EXISTS motivazione (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idProposta INT,
	azioneProposta ENUM('APPOGGIA','BOCCIA','ASTIENE'),
	concordi INT,
	descrizione VARCHAR(100)
	);
--	FOREIGN KEY (idProposta) REFERENCES proposta(id)
--	ON DELETE CASCADE

-- Votazioni richieste ad un "gestore di assemblea".
CREATE TABLE IF NOT EXISTS votazioni_richieste (
	idGdA INT,
	idProposta INT
	);
	
--	foreign key (idGdA) references gestore_di_assemblea(id),
--	foreign key (idProposta) references proposta(id)
--	ON DELETE CASCADE

-- Registra le votazioni effettuate, non il "si/no/astenuto"
CREATE TABLE IF NOT EXISTS votazioni_effettuate (
	idUtente INT,
	idProposta INT,
	idGDA INT,
	idRichiesta INT
	);
--	, PRIMARY KEY(idUtente,idProposta),
--	FOREIGN KEY (idProposta) REFERENCES proposte(id),
--	FOREIGN KEY (idUtente) REFERENCES utente(id),
--	FOREIGN KEY (idRichiesta) REFERENCES votazioni_richieste(id)
--		ON DELETE CASCADE




