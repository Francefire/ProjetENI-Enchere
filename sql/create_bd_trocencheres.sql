-- Script de cr�ation de la base de donn�es ENCHERES
--   type :      SQL Server 2012
--



CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     datetime NOT NULL,
	--Ajout de la contrainte CK_Montant_Positif car une enchere ne peut etre negatif.
	montant_enchere  INTEGER NOT NULL CONSTRAINT CK_Montant_Positif CHECK (montant_enchere >= 0)

)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS (
	no_article         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(20) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30),
    code_postal      VARCHAR(10),
    ville            VARCHAR(30),
	-- Modification du type du mot de passe pour prevoir le hashage sha256
    mot_de_passe     CHAR(64) NOT NULL,
	-- Ajout de la contrainte CK_Credit_Positif car un credit ne peut pas etre negatif
    credit           INTEGER NOT NULL CONSTRAINT CK_Credit_Positif CHECK (credit >= 0),
	-- Ajout d'une valeur par defaut a 0 car un utilisateur n'est pas admin par defaut
    administrateur   bit CONSTRAINT DF_Admin_False DEFAULT 0,
	-- Ajout de la colonne active : qui permet d'activer ou de desactiver le compte, uniquement modifiable par l'administrateur
	active			 bit CONSTRAINT DF_Active DEFAULT 1 
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)
ALTER TABLE UTILISATEURS ADD CONSTRAINT DF_Credit DEFAULT 0


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
	--Ajout des contraintes CK_Date_Fin_Debut, car la date de fin d'une enchere ne peut pas etre anterieur a la date de debut de l'enchere
    date_fin_encheres             DATE NOT NULL CONSTRAINT CK_Date_Fin_Debut CHECK (date_debut_encheres <= date_fin_encheres),
	--Ajout de la contrainte CK_Prix_Initial_Positif car le prix initial ne peut etre negatif
    prix_initial                  INTEGER NOT NULL CONSTRAINT CK_Prix_Initial_Positif CHECK (prix_initial >= 0),
	--Ajout de la contrainte CK_Prix_Vente car le prix de vente doit etre superieur au prix initial.
    prix_vente                    INTEGER CONSTRAINT CK_Prix_Vente CHECK (prix_vente > prix_initial),
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
)

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE CASCADE 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE CASCADE 
    ON UPDATE no action 

