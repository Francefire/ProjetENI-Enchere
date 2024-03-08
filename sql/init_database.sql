-- mot de passe des utilisateurs : ENI3438eni

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur, desactive) VALUES ('admin', 'Admin', 'Admin', 'admin@admin.admin', '0605040302','rue de administrative', '70000', 'Admin City', '3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4', 1, 0);
  
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, administrateur, desactive) VALUES ('joe', 'Bel', 'Joe', 'joe.bel@example.com', '0545639586', 'rue du champ', '20453', 'Belleville', '3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4',	0, 1);
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('DEVMJ','JODAR','Mario','mario.jodar2023@campus-eni.fr','0601020304','rue du la street', '38000','GRENOBLE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('MAUD','LEMAIRE','Maud','maud.lemaire2023@campus-eni.fr','0601020304','rue du la street', '81000','RABASTENS','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('MAX', 'MICHAUD', 'Maxime', 'maxime.michaud2023@campus-eni.fr', '0601020304','rue du campus ENI', '01234','VILLE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('TOUHAMI','AGLAGAL','Touhami','touhami.aglagal2023@campus-eni.fr','0601020304','rue du la street', '85000','FONTENAY LE COMTE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('FRANCOIS','BLITTE','Francois','francois.blitte2023@campus-eni.fr','0601020304','rue du la Geekerie', '01234','LAVAL','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('EBRU','JOLIVET','Ebru','ebru.jolivet2023@campus-eni.fr','0601020304','rue du la street', '01234','PAU','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('YOUSSEF','REVERBEL','Youssef','youssef.reverbel2023@campus-eni.fr','0601020304','rue du la street', '34000','MONTPELLIER','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('LAETITIA','GIMENO','Laetitia','laetitia.gimeno2023@campus-eni.fr','0601020304','rue du la street', '34000','MONTPELLIER','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('OLEKSANDR','MUKHIN','Oleksandr','oleksandr.mukhin2023@campus-eni.fr','0601020304','rue du la street', '01234','VILLE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('MAXIME','VOZELLE','Maxime','maxime.vozelle2024@campus-eni.fr','0601020304','rue du la street', '01234','VILLE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES ('EMMANUEL','MALABRY','Emmanuel','emalabry@campus-eni.fr','0601020304','rue du campus ENI', '01234','VILLE','3724371969645388abbe172431698200b8d97d96f91fb80682611356929a78f4')

INSERT INTO CATEGORIES (libelle) VALUES ('Mobilier');
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES ('Vetements');
INSERT INTO CATEGORIES (libelle) VALUES ('Sport');
INSERT INTO CATEGORIES (libelle) VALUES ('Jouets');
INSERT INTO CATEGORIES (libelle) VALUES ('Electromenager');

-- Inserer un article dont l'enchere n'a pas commencé, on est le 08/03/2024
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, url_image, no_utilisateur, no_categorie) VALUES ('Chaise', 'Chaise en bois', '2024-03-10', '2024-03-17', 50, 50,'/public/_fd5f1ba2-f453-4bae-9293-f8e59d4006e5.jpg', 2, 1);

-- Inserer un article dont l'enchere est en cours, on est le 08/03/2024
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES ('Ordinateur', 'Ordinateur portable', '2024-03-01', '2024-03-15', 500, 500, 2, 2);

-- Inserer un article dont l'enchere est terminée, on est le 08/03/2024
INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES ('T-shirt', 'T-shirt en coton', '2024-02-01', '2024-02-15', 10, 15, 2, 3);


-- Inserer une enchere pour l'article dont l'enchere est terminé par l'utilisateur 1
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (2, 3, '2024-02-10', 16);

-- Inserer une enchere pour l'article dont l'enchere est en cours par l'utilisateur 1
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (2, 2, '2024-03-08', 600);

