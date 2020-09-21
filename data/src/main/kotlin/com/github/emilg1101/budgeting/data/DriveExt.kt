package com.github.emilg1101.budgeting.data

import com.google.api.client.http.ByteArrayContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

suspend fun Drive.createFile(filename: String) = withContext(Dispatchers.IO) {
    val metadata: File = File()
        .setParents(Collections.singletonList("appDataFolder"))
        .setMimeType("text/plain")
        .setName(filename)

    files().create(metadata).execute().id
        ?: throw IOException("Null result when requesting file creation.")
}

suspend fun Drive.readFile(fileId: String) = withContext(Dispatchers.IO) {
    val metadata: File = files().get(fileId).execute()
    val name = metadata.name

    files().get(fileId).executeMediaAsInputStream().use { `is` ->
        BufferedReader(InputStreamReader(`is`)).use { reader ->
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val contents = stringBuilder.toString()
            name to contents
        }
    }
}

suspend fun Drive.saveFile(fileId: String, filename: String, content: String) = withContext(Dispatchers.IO) {
    val contentStream = ByteArrayContent.fromString("text/plain", content)
    val metadata = File().setName(filename)
    files().update(fileId, metadata, contentStream).execute()
}

suspend fun Drive.queryFiles(): FileList =
    withContext(Dispatchers.IO) { files().list().setSpaces("appDataFolder").execute() }
