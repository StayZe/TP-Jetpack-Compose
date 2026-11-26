package com.example.tp_jetpack_compose_antoine_mandra

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tp_jetpack_compose_antoine_mandra.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp_jetpack_compose_antoine_mandra.ui.theme.TPJetpackComposeAntoineMandraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPJetpackComposeAntoineMandraTheme {
                ProfilScreen(
                    nom = "Antoine Mandra",
                    titre = "Développeur Android junior",
                    description = "Je conçois des applications Android avec Jetpack Compose à mes heures perdues. J'ai déjà trouvé une alternance.",
                    competences = listOf("Kotlin", "Jetpack Compose", "Git"),
                    projets = listOf(
                        Projet("TP Jetpack Compose", "Fiche profil développeur en Compose."),
                        Projet("Application de notes", "Liste de notes avec cartes réutilisables.")
                    ),
                    contact = "a.mandra@myskolae.fr",
                    disponibilite = "Déjà pris",
                    // Action fictive : pas de vraie action pour ce devoir
                    onContact = {}
                )
            }
        }
    }
}

// Données locales d'un projet ou d'une expérience
data class Projet(val titre: String, val description: String)

// Écran principal : une LazyColumn qui affiche tout le profil
@Composable
fun ProfilScreen(
    nom: String,
    titre: String,
    description: String,
    competences: List<String>,
    projets: List<Projet>,
    contact: String,
    disponibilite: String,
    onContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { EnTete(nom = nom, titre = titre) }
            item { Information(texte = description) }

            item { TitreSection("Compétences") }
            // LazyRow : compétences défilables horizontalement
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(competences) { TechnologyChip(texte = it) }
                }
            }

            item { TitreSection("Projets et expériences") }
            items(projets) { ProjectCard(projet = it) }

            item { ContactCard(contact = contact, disponibilite = disponibilite) }

            item {
                Button(onClick = onContact, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Email, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Contacter")
                }
            }
        }
    }
}

// Avatar placeholder superposé à un badge
@Composable
fun EnTete(nom: String, titre: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(80.dp)) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Photo de profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            // Badge superposé en bas à droite
            Surface(
                modifier = Modifier.align(Alignment.BottomEnd).size(24.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = nom, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = titre,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TitreSection(texte: String, modifier: Modifier = Modifier) {
    Text(text = texte, style = MaterialTheme.typography.titleSmall, modifier = modifier)
}

// Description dans une zone colorée
@Composable
fun Information(texte: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = texte,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Une compétence sous forme de puce
@Composable
fun TechnologyChip(texte: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier, shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.secondaryContainer) {
        Text(
            text = texte,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// Un projet ou une expérience
@Composable
fun ProjectCard(projet: Projet, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Build, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = projet.titre, style = MaterialTheme.typography.titleSmall)
            }
            Text(
                text = projet.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// Zone de contact : champs en lecture seule
@Composable
fun ContactCard(contact: String, disponibilite: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Contact", style = MaterialTheme.typography.titleSmall)
            OutlinedTextField(
                value = contact,
                onValueChange = {},
                readOnly = true,
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = disponibilite,
                onValueChange = {},
                readOnly = true,
                label = { Text("Disponibilité") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Previews : deux jeux de données clairs et un en thème sombre
@Preview(showBackground = true)
@Composable
fun PreviewProfilJunior() {
    TPJetpackComposeAntoineMandraTheme {
        ProfilScreen(
            nom = "Léa Martin",
            titre = "Développeuse Android junior",
            description = "Je conçois des applications Android avec Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            projets = listOf(Projet("Application météo", "Affichage des prévisions sur 7 jours.")),
            contact = "lea.martin@exemple.fr",
            disponibilite = "Disponible en alternance dès septembre",
            onContact = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilFullStack() {
    TPJetpackComposeAntoineMandraTheme {
        ProfilScreen(
            nom = "Antoine Durand",
            titre = "Développeur full-stack",
            description = "Je développe des applications web de bout en bout.",
            competences = listOf("React", "Node.js", "PostgreSQL", "Docker"),
            projets = listOf(Projet("Site e-commerce", "Front React et API Node.js.")),
            contact = "antoine.durand@exemple.fr",
            disponibilite = "Non disponible pour le moment",
            onContact = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewProfilSombre() {
    TPJetpackComposeAntoineMandraTheme {
        ProfilScreen(
            nom = "Léa Martin",
            titre = "Développeuse Android junior",
            description = "Je conçois des applications Android avec Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            projets = listOf(Projet("Application météo", "Affichage des prévisions sur 7 jours.")),
            contact = "lea.martin@exemple.fr",
            disponibilite = "Disponible en alternance dès septembre",
            onContact = {}
        )
    }
}
