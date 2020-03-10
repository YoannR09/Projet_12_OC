
CREATE SEQUENCE public.categorie_id_seq;

CREATE TABLE public.categorie (
                id INTEGER NOT NULL DEFAULT nextval('public.categorie_id_seq'),
                nom VARCHAR NOT NULL,
                labelle VARCHAR NOT NULL,
                disponible BOOLEAN NOT NULL,
                CONSTRAINT categorie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.categorie_id_seq OWNED BY public.categorie.id;

CREATE SEQUENCE public.taille_id_seq;

CREATE TABLE public.taille (
                id INTEGER NOT NULL DEFAULT nextval('public.taille_id_seq'),
                taille VARCHAR NOT NULL,
                CONSTRAINT taille_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.taille_id_seq OWNED BY public.taille.id;

CREATE SEQUENCE public.article_id_seq;

CREATE TABLE public.article (
                id INTEGER NOT NULL DEFAULT nextval('public.article_id_seq'),
                nom VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                prix REAL NOT NULL,
                categorie_id INTEGER NOT NULL,
                disponible BOOLEAN NOT NULL,
                reference VARCHAR NOT NULL,
                CONSTRAINT article_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;

CREATE SEQUENCE public.image_id_seq;

CREATE TABLE public.image (
                id INTEGER NOT NULL DEFAULT nextval('public.image_id_seq'),
                url VARCHAR NOT NULL,
                labelle VARCHAR NOT NULL,
                article_id INTEGER NOT NULL,
                CONSTRAINT image_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;

CREATE SEQUENCE public.list_taille_id_seq;

CREATE TABLE public.list_taille (
                id INTEGER NOT NULL DEFAULT nextval('public.list_taille_id_seq'),
                taille_id INTEGER NOT NULL,
                article_id INTEGER NOT NULL,
                CONSTRAINT list_taille_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.list_taille_id_seq OWNED BY public.list_taille.id;

CREATE SEQUENCE public.niveau_acces_id_seq;

CREATE TABLE public.niveau_acces (
                id INTEGER NOT NULL DEFAULT nextval('public.niveau_acces_id_seq'),
                niveau INTEGER NOT NULL,
                labelle VARCHAR NOT NULL,
                CONSTRAINT niveau_acces_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.niveau_acces_id_seq OWNED BY public.niveau_acces.id;

CREATE SEQUENCE public.adresse_id_seq;

CREATE TABLE public.adresse (
                id INTEGER NOT NULL DEFAULT nextval('public.adresse_id_seq'),
                ville VARCHAR NOT NULL,
                code_postal VARCHAR NOT NULL,
                rue VARCHAR NOT NULL,
                numero VARCHAR NOT NULL,
                info VARCHAR NOT NULL,
                CONSTRAINT adresse_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.adresse_id_seq OWNED BY public.adresse.id;

CREATE SEQUENCE public.compte_id_seq;

CREATE TABLE public.compte (
                id INTEGER NOT NULL DEFAULT nextval('public.compte_id_seq'),
                email VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                nom VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                niveau_acces_id INTEGER NOT NULL,
                numero_telephone VARCHAR NOT NULL,
                adresse_id INTEGER NOT NULL,
                CONSTRAINT compte_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.compte_id_seq OWNED BY public.compte.id;

CREATE SEQUENCE public.adresse_livraison_id_seq;

CREATE TABLE public.adresse_livraison (
                id INTEGER NOT NULL DEFAULT nextval('public.adresse_livraison_id_seq'),
                compte_id INTEGER NOT NULL,
                adresse_id INTEGER NOT NULL,
                CONSTRAINT adresse_livraison_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.adresse_livraison_id_seq OWNED BY public.adresse_livraison.id;

CREATE SEQUENCE public.panier_id_seq;

CREATE TABLE public.panier (
                id INTEGER NOT NULL DEFAULT nextval('public.panier_id_seq'),
                compte_id INTEGER NOT NULL,
                CONSTRAINT panier_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.panier_id_seq OWNED BY public.panier.id;

CREATE SEQUENCE public.contenu_id_seq;

CREATE TABLE public.contenu (
                id INTEGER NOT NULL DEFAULT nextval('public.contenu_id_seq'),
                quantite INTEGER NOT NULL,
                panier_id INTEGER NOT NULL,
                taille_id INTEGER NOT NULL,
                article_id INTEGER NOT NULL,
                CONSTRAINT contenu_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.contenu_id_seq OWNED BY public.contenu.id;

CREATE SEQUENCE public.statut_id_seq;

CREATE TABLE public.statut (
                id INTEGER NOT NULL DEFAULT nextval('public.statut_id_seq'),
                niveau INTEGER NOT NULL,
                labelle VARCHAR NOT NULL,
                CONSTRAINT statut_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.statut_id_seq OWNED BY public.statut.id;

CREATE SEQUENCE public.commande_id_seq;

CREATE TABLE public.commande (
                id INTEGER NOT NULL DEFAULT nextval('public.commande_id_seq'),
                numero VARCHAR NOT NULL,
                date DATE NOT NULL,
                statut_id INTEGER NOT NULL,
                compte_id INTEGER NOT NULL,
                adresse_id INTEGER NOT NULL,
                CONSTRAINT commande_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.commande_id_seq OWNED BY public.commande.id;

CREATE SEQUENCE public.ligne_de_commande_id_seq;

CREATE TABLE public.ligne_de_commande (
                id INTEGER NOT NULL DEFAULT nextval('public.ligne_de_commande_id_seq'),
                designation VARCHAR NOT NULL,
                commande_id INTEGER NOT NULL,
                quantite VARCHAR NOT NULL,
                taille VARCHAR NOT NULL,
                montant REAL NOT NULL,
                prix_unit REAL NOT NULL,
                CONSTRAINT ligne_de_commande_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.ligne_de_commande_id_seq OWNED BY public.ligne_de_commande.id;

ALTER TABLE public.article ADD CONSTRAINT categorie_vetement_fk
FOREIGN KEY (categorie_id)
REFERENCES public.categorie (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.list_taille ADD CONSTRAINT taille_list_taille_fk
FOREIGN KEY (taille_id)
REFERENCES public.taille (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.contenu ADD CONSTRAINT taille_contenu_fk
FOREIGN KEY (taille_id)
REFERENCES public.taille (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.list_taille ADD CONSTRAINT article_list_taille_fk
FOREIGN KEY (article_id)
REFERENCES public.article (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.contenu ADD CONSTRAINT article_contenu_fk
FOREIGN KEY (article_id)
REFERENCES public.article (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.image ADD CONSTRAINT article_image_fk
FOREIGN KEY (article_id)
REFERENCES public.article (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.compte ADD CONSTRAINT niveau_acces_compte_fk
FOREIGN KEY (niveau_acces_id)
REFERENCES public.niveau_acces (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.compte ADD CONSTRAINT adresse_compte_fk
FOREIGN KEY (adresse_id)
REFERENCES public.adresse (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commande ADD CONSTRAINT adresse_commande_fk
FOREIGN KEY (adresse_id)
REFERENCES public.adresse (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.adresse_livraison ADD CONSTRAINT adresse_adresse_livraison_fk
FOREIGN KEY (adresse_id)
REFERENCES public.adresse (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.panier ADD CONSTRAINT compte_panier_fk
FOREIGN KEY (compte_id)
REFERENCES public.compte (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commande ADD CONSTRAINT compte_commande_fk
FOREIGN KEY (compte_id)
REFERENCES public.compte (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.adresse_livraison ADD CONSTRAINT compte_adresse_livraison_fk
FOREIGN KEY (compte_id)
REFERENCES public.compte (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.contenu ADD CONSTRAINT panier_contenu_fk
FOREIGN KEY (panier_id)
REFERENCES public.panier (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commande ADD CONSTRAINT statut_commande_fk
FOREIGN KEY (statut_id)
REFERENCES public.statut (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ligne_de_commande ADD CONSTRAINT commande_ligne_de_commande_fk
FOREIGN KEY (commande_id)
REFERENCES public.commande (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;