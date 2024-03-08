INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur, desactive) 
VALUES (
	'admin', 'Admin', 'Admin', 'admin@admin.admin', '0605040302', 
	'rue de administrative', '70000', 'Admin City', 
	'C30DB455C7A0BD7532830424CDACDA8365818E6425995B07F186C9D6C6CED38A',
	1, 0
);

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur, desactive) 
VALUES (
	'joe', 'Bel', 'Joe', 'joe.bel@example.com', '0545639586', 
	'rue du champ', '20453', 'Belleville', 
	'4f0d24b941645161ed5ee39bc053f9ea5afc63f19ee3a0f4323f2092851886c1',
	0, 0
);

INSERT INTO CATEGORIES (libelle) VALUES ('Mobilier');
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');