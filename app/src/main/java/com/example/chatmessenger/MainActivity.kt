package com.example.chatmessenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatmessenger.ui.theme.ChatMessengerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatList()
        }
    }
}

@Composable
fun ChatItem(name: String, lastMessage: String, unread: Int, hasImportant: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, style = MaterialTheme.typography.headlineLarge)
            Text(text = lastMessage, style = MaterialTheme.typography.bodyLarge)
        }
        if (unread > 0) {
            Badge(
                text = if (unread > 99) "99+" else "$unread",
                textColor = if (hasImportant) Color.White else Color.Gray,
                backgroundColor = if (hasImportant) Color.Red else Color.Blue
            )
        }
    }
}

@Composable
fun Badge(text: String, textColor: Color, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(backgroundColor, shape = MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.bodyLarge)
    }
}
@Preview(showBackground = true)
@Composable
fun ChatList() {
    val chatItems = listOf(
        ChatData("Alice", "Hey, how are you?", 2, true),
        ChatData("Bob", "See you later!", 0, false),
        ChatData("Charlie", "Let's catch up!", 5, false),
        ChatData("Man", "Hey, how are you?", 2, true),
        ChatData("Boy", "See you later!", 100, false),
        ChatData("Rj", "Let's catch up!", 78, false)
    )

    LazyColumn {
        items(chatItems.size) { index ->
            val chat = chatItems[index]
            ChatItem(chat.name, chat.lastMessage, chat.unread, chat.hasImportant)
        }
    }
}

data class ChatData(val name: String, val lastMessage: String, val unread: Int, val hasImportant: Boolean)
