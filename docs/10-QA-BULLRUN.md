# P010 — QA, Audit & Bullrun

## Audit final
Le contrôle final vérifie :
1. cohérence documentaire P000→P010 ;
2. présence du Core ;
3. séparation des responsabilités ;
4. absence de secrets évidents ;
5. contrats de lifecycle ;
6. règles de sécurité ;
7. structure de données ;
8. reproductibilité des résultats ;
9. tests automatisables ;
10. état de déploiement explicitement qualifié.

## Bullrun
Bullrun = test de charge et de cohérence contrôlé, pas une attaque externe.

### Niveaux
- B0 : validation statique du dépôt.
- B1 : build du plugin.
- B2 : démarrage Paper local.
- B3 : simulations de connexions/matchs.
- B4 : montée en charge contrôlée.
- B5 : endurance.

## Stop conditions
Le test s'arrête en cas de corruption de données, fuite de secret, crash critique répété ou dépassement d'un seuil de sécurité défini.

## Résultat
Un PASS Bullrun doit être fondé sur des logs et artefacts réellement produits par l'exécution. Une simple présence de fichiers ne vaut pas validation runtime.
