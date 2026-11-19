# Projet PCF

## 1. Auteurs

- Reda YAOU
- Demba GANDEGA

## 2. Choix d'implémentation

L'implémentation suit globalement les préconisations des sujets de TP.

## 3. Fonctionnalités et limitations

Les PCF vert, bleu et rouge ont été implémentés au complet (avec l'évaluation et le typage).

L'analyse syntaxique a été faite et testée avec ANTLR Preview pour tous les PCFs (y compris PCF noir).

L'évaluation et le typage de PCF noir (fonctionalité fix) manquent.

## 4. Réponse à la question de la section 5 du TP sur le typage

Nous avons pensé à l'exemple suivant:

```pcf
let id = fun x -> x in
  let a = id 1 in
    id (fun y -> y)
```

Lors de l'inférence, `id` reçoit le type `T -> T`, où `T` est une variable de type inconnue.

La première application `id 5` implique `T = INT` par unification.

Ensuite, la deuxième application tente d'unifier `INT` avec un type fonction, ce qui échoue.

Donc avec notre implémentation actuelle nous ne pouvons pas aplliquer la même fonction identité à des arguments de types différents.

## 5. Utilisation de l'IA générative

- **Plugin AI Assistant** :
    - Aide à l'écriture et en particulier avec la syntaxe de Scala

## 6. Tests

Des tests sur l'évaluation et le typage sont disponibles en lançant src/test/Test.scala

Les résultats attendus des tests sont écrits en commentaire sur ce fichier.

Ces tests utilisent les fichiers présents dans le répertoire /test.

Les programmes PCF créés permettent de vérifier :
- Les opérations arithmétiques.
- Les conditions `ifz`.
- Les variables et déclarations `let`.
- Les fonctions et applications.
- L'inférence de types.
- Le rejet des programmes mal typés.
