# ChampsProteges

Plugin Paper minimaliste : **sauter sur une terre labourée ne la détruit plus**,
ni pour les joueurs ni pour les mobs. Rien d'autre.

Écrit pour le serveur « Les coccinelles » (Paper 26.3), dont la règle est de
rester le plus vanilla possible : on évite d'installer WorldGuard + WorldEdit
juste pour une option.

## Fonctionnement

Trois écouteurs d'événements, tous en priorité `LOWEST` :

| Événement | Cas couvert |
|---|---|
| `PlayerInteractEvent` (action `PHYSICAL` sur `FARMLAND`) | Un joueur saute ou atterrit sur un champ |
| `EntityInteractEvent` (bloc `FARMLAND`) | Un mob saute ou atterrit sur un champ |
| `EntityChangeBlockEvent` (`FARMLAND` vers `DIRT`) | Filet de sécurité pour toute autre entité |

Le dessèchement naturel d'un champ non irrigué (`BlockFadeEvent`) n'est **pas**
bloqué : le comportement vanilla est conservé.

Aucune commande, aucune permission, aucun fichier de configuration.

## Coût

| Ressource | Valeur |
|---|---|
| RAM | négligeable (une classe chargée, aucun état) |
| CPU | négligeable : un test de type de bloc par événement concerné |
| Disque | jar de 3,4 Ko |

## Compilation

Paper 26.x est en bytecode Java 25 : il faut un JDK 25.

```bash
JAVA_HOME=/opt/jdk25 /opt/maven/bin/mvn -q package
# -> target/ChampsProteges.jar
```

Dépendance : `io.papermc.paper:paper-api` (scope `provided`). L'API est stable
pour ces événements, le jar compilé contre 26.1 fonctionne en 26.3.

## Déploiement

```bash
cp target/ChampsProteges.jar /home/amp/.ampdata/instances/Coccinelles01/Minecraft/plugins/
chown amp:amp .../plugins/ChampsProteges.jar
# puis redémarrage du serveur
```

Vérification en jeu ou en console : `plugins` doit lister `ChampsProteges`.

## Entretien

Rien de particulier. À recompiler uniquement si une future version de Paper
change ces événements (peu probable).
