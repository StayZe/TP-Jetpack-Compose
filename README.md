# Profil développeur Compose

Application Android Jetpack Compose affichant une fiche « Profil développeur ». L'écran principal présente le nom, le titre professionnel, une courte description, une liste de compétences, une information de contact et une zone de disponibilité. Un bouton « Contacter » et une zone cliquable permettent d'interagir avec la fiche. Les clics appellent un callback vide, sans modifier l'interface.

## Notions utilisées

- Composables
- Paramètres
- Modifiers (chaînage, `modifier: Modifier = Modifier`)
- Padding externe / interne
- Callbacks
- Previews
- Accessibilité de base

## Explication courte

**1. Quels composables avez-vous créés ?**
`ProfilScreen`, `ProfilCard`, `EnTete`, `Competence`, `Information`, `DisponibiliteZone` et `ActionBouton`.

**2. Quel composable représente l'écran principal ?**
`ProfilScreen`.

**3. Où utilisez-vous un padding externe ?**
Dans `ProfilScreen` : `Modifier.padding(innerPadding).padding(16.dp)` est passé à `ProfilCard`. C'est le parent qui décide de la place de la fiche dans l'écran.

**4. Où utilisez-vous un padding interne ?**
Dans `ProfilCard` : `Column(modifier = Modifier.padding(16.dp))` espace le contenu à l'intérieur de la fiche.

**5. Quel composant reçoit un callback ?**
`ProfilCard` reçoit `onContact`, qu'il transmet à `DisponibiliteZone` et à `ActionBouton`. Les deux appellent ce callback au clic.

**6. Quelle information d'accessibilité avez-vous ajoutée ?**
`DisponibiliteZone` est une zone cliquable personnalisée. Elle reçoit un rôle `Role.Button` (`clickable(role = Role.Button, ...)`) et une description claire via `semantics { contentDescription = ... }`, compréhensible pour un lecteur d'écran. `ActionBouton` utilise un `Button` : ce composant expose déjà son rôle de bouton et son texte aux outils d'accessibilité, il n'a donc pas besoin d'information supplémentaire. Il est plus explicite qu'une simple zone cliquable personnalisée.

**7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**
Chaque composant reçoit ses données en paramètres (nom, titre, compétences, callback…) au lieu d'afficher des valeurs figées. Il accepte aussi un `modifier` et l'applique à son élément racine, ce qui laisse le parent décider de son placement. `Competence` et `Information` peuvent ainsi être réutilisés avec d'autres données.

## Previews

Deux previews montrent des variantes différentes de la fiche :
- `PreviewProfilJunior` : profil Android junior, disponible en alternance.
- `PreviewProfilFullStack` : profil full-stack, avec d'autres compétences et une disponibilité différente.

## Capture d'écran
<img width="1512" height="945" alt="image" src="https://github.com/user-attachments/assets/54bf0312-deb3-4ed9-857f-622bbee232ce" />
<img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/8d6af00a-da4b-4b7e-849d-de5e00d39f13" />
