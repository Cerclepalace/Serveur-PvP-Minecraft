# P003 — PLAYER ENGINE

**Statut : SPECIFICATION**  
**Phase : P003**  
**Projet : Serveur-PvP-Minecraft**

## 1. Objectif

Construire le moteur de profilage comportemental du joueur à partir de ses actions réelles dans le serveur Minecraft.

Le Player Engine sert à alimenter ultérieurement la progression, le matchmaking, les recommandations de gameplay, les événements et certaines fonctions IA.

## 2. Principe fondamental

Le système observe des **comportements de jeu**, pas la personne réelle.

Le profil ne doit pas tenter de déterminer :

- l'âge réel du joueur ;
- son intelligence ;
- ses capacités cognitives ;
- son état psychologique ou médical ;
- sa personnalité réelle ;
- toute caractéristique personnelle non nécessaire au fonctionnement du jeu.

Le profil décrit uniquement ce que le joueur fait dans l'environnement du serveur.

## 3. Dimensions comportementales

Le moteur utilise plusieurs dimensions indépendantes :

### COMBAT

Signaux possibles :

- participation aux combats ;
- performance par mode ;
- régularité ;
- efficacité selon les règles du mode ;
- choix de difficulté ;
- progression des performances.

### BUILD

Signaux possibles :

- temps consacré à la construction ;
- volume et fréquence des constructions ;
- diversité des matériaux utilisés ;
- participation à des activités de construction.

### FARM

Signaux possibles :

- récolte ;
- production ;
- fréquence des activités de farm ;
- utilisation des ressources obtenues.

### EXPLORE

Signaux possibles :

- zones visitées ;
- diversité des zones explorées ;
- fréquence d'exploration ;
- progression géographique dans le serveur.

### SOCIAL

Signaux possibles :

- interactions avec d'autres joueurs ;
- participation aux événements collectifs ;
- activités d'équipe ;
- participation aux espaces sociaux.

### STRATEGY

Signaux possibles :

- choix de modes ;
- choix de kits ou configurations lorsqu'ils existent ;
- adaptation à différentes situations de jeu ;
- constance des choix selon les contextes.

## 4. Architecture logique

```text
PLAYER ACTIONS
      │
      ▼
EVENT COLLECTION
      │
      ▼
NORMALIZATION
      │
      ▼
FEATURE ENGINEERING
      │
      ▼
PLAYER PROFILE
      │
      ├── Progression
      ├── Matchmaking
      ├── Recommandations
      ├── Événements
      └── IA
```

## 5. Données minimales

Chaque événement doit être réduit au minimum nécessaire à son usage.

Exemple conceptuel :

```text
player_id
session_id
timestamp
event_type
mode
value
metadata_minimal
```

Le système privilégie les agrégats utiles plutôt que la conservation illimitée d'événements bruts.

## 6. Profil dynamique

Le profil joueur n'est pas une étiquette permanente.

Il doit pouvoir évoluer avec les nouvelles observations :

```text
Profil initial
    ↓
Observations
    ↓
Agrégation
    ↓
Profil actuel
    ↓
Nouvelles observations
    ↓
Réévaluation
```

Un joueur peut simultanément avoir plusieurs tendances fortes, par exemple :

```text
COMBAT     : élevé
BUILD      : moyen
FARM       : faible
EXPLORE    : élevé
SOCIAL     : moyen
STRATEGY   : élevé
```

Aucune dimension ne doit être considérée comme une identité définitive.

## 7. Niveau initial

À la première connexion :

```text
PLAYER LEVEL = 1
PROFILE = UNKNOWN / INITIAL
```

Le système accumule ensuite suffisamment de données avant de produire des signaux comportementaux exploitables.

Une absence de données ne doit pas être interprétée comme une faiblesse.

## 8. Anti-gaming

Le Player Engine doit anticiper les comportements artificiels destinés à manipuler le profil.

Contrôles prévus :

- pondération par durée réelle ;
- détection d'actions répétitives artificielles ;
- plafonnement de certains gains ;
- séparation entre activité utile et bruit ;
- validation de cohérence des événements ;
- impossibilité de générer une progression illimitée par une seule action répétée.

## 9. Utilisation future par l'IA

L'IA pourra exploiter le profil pour proposer des expériences adaptées au comportement de jeu :

- suggestion d'un mode correspondant aux habitudes ;
- proposition d'un entraînement ;
- orientation vers un événement ;
- recommandation de contenu ;
- adaptation de l'expérience du hub.

L'IA ne doit pas transformer le profil de jeu en diagnostic personnel.

## 10. Gouvernance des données

Principes :

1. minimisation des données ;
2. finalité explicite ;
3. séparation entre données techniques et profil comportemental ;
4. contrôle des accès ;
5. possibilité d'expliquer les principaux signaux utilisés ;
6. conservation limitée lorsque les données ne sont plus nécessaires ;
7. aucune inférence personnelle non nécessaire au jeu.

## 11. Critères de validation P003

P003 sera considéré comme techniquement spécifié lorsque les points suivants seront définis :

- [x] dimensions comportementales ;
- [x] événements observables ;
- [x] pipeline de transformation ;
- [x] profil dynamique ;
- [x] niveau initial ;
- [x] mécanismes anti-manipulation ;
- [x] limites d'utilisation de l'IA ;
- [x] principes de minimisation des données ;
- [ ] schéma de stockage réel ;
- [ ] instrumentation serveur ;
- [ ] tests avec événements simulés ;
- [ ] tests avec joueurs réels ;
- [ ] validation de la précision des agrégations.

## 12. Gate P003

**Décision actuelle : SPECIFICATION READY — IMPLEMENTATION NOT YET VALIDATED.**

Le prochain niveau est l'implémentation instrumentée et la validation sur données de jeu contrôlées.
