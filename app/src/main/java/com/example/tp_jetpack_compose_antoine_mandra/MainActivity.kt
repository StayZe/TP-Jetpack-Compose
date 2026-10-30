package com.example.tp_jetpack_compose_antoine_mandra

import com.example.tp_jetpack_compose_antoine_mandra.ui.theme.TPJetpackComposeAntoineMandraTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPJetpackComposeAntoineMandraTheme {
                ProfilScreen()
            }
        }
    }
}

// Écran principal
@Composable
fun ProfilScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ProfilCard(
            nom = "Antoine Mandra",
            titre = "Développeur Android junior",
            description = "Je conçois des applications Android avec Jetpack Compose à mes heures perdues. J'ai déjà trouvé une alternance.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            disponibilite = "Déjà pris",
            contact = "a.mandra@myskolae.fr",
            onContact = {},
            // padding externe : décidé par le parent
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}

// Fiche profil
@Composable
fun ProfilCard(
    nom: String,
    titre: String,
    description: String,
    competences: List<String>,
    disponibilite: String,
    contact: String,
    onContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 2.dp
    ) {
        // padding interne : espace le contenu de la fiche
        Column(modifier = Modifier.padding(16.dp)) {
            EnTete(nom = nom, titre = titre)
            Information(texte = description, modifier = Modifier.padding(top = 12.dp))

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 12.dp)
            )
            competences.forEach { Competence(texte = it) }

            Information(texte = contact, modifier = Modifier.padding(top = 12.dp))

            DisponibiliteZone(
                texte = disponibilite,
                onClick = onContact,
                modifier = Modifier.padding(top = 12.dp)
            )

            ActionBouton(
                texte = "Contacter",
                onClick = onContact,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

// En-tête : nom et titre
@Composable
fun EnTete(nom: String, titre: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = nom, style = MaterialTheme.typography.headlineMedium)
        Text(text = titre, style = MaterialTheme.typography.titleMedium)
    }
}

// Une compétence
@Composable
fun Competence(texte: String, modifier: Modifier = Modifier) {
    Text(
        text = "• $texte",
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier.padding(top = 4.dp)
    )
}

// Une information secondaire
@Composable
fun Information(texte: String, modifier: Modifier = Modifier) {
    Text(
        text = texte,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

// Zone cliquable personnalisée avec accessibilité
// Chaîne : padding (zone cliquable incluse) -> largeur -> clic -> sémantique
@Composable
fun DisponibiliteZone(
    texte: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = texte,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "Voir la disponibilité : $texte" }
    )
}

// Bouton d'action
@Composable
fun ActionBouton(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Text(texte)
    }
}

// Previews : deux jeux de données différents
@Preview(showBackground = true)
@Composable
fun PreviewProfilJunior() {
    TPJetpackComposeAntoineMandraTheme {
        ProfilCard(
            nom = "Léa Martin",
            titre = "Développeuse Android junior",
            description = "Je conçois des applications Android avec Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            disponibilite = "Disponible en alternance dès septembre",
            contact = "lea.martin@exemple.fr",
            onContact = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilFullStack() {
    TPJetpackComposeAntoineMandraTheme {
        ProfilCard(
            nom = "Antoine Durand",
            titre = "Développeur full-stack",
            description = "Je développe des applications web de bout en bout.",
            competences = listOf("React", "Node.js", "PostgreSQL", "Docker"),
            disponibilite = "Non disponible pour le moment",
            contact = "antoine.durand@exemple.fr",
            onContact = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}