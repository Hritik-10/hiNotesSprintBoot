package com.hritech.hinotes.Controller;

import com.hritech.hinotes.Model.Notes;
import com.hritech.hinotes.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*") // Optional: to allow requests from frontend (like React)
public class NotesController {

    @Autowired
    private NotesService notesService;

    // 🔍 Get all notes
    @GetMapping("/fetchallnotes")
    public ResponseEntity<List<Notes>> getAllNotes() {
        return ResponseEntity.ok(notesService.fetchAllNotes());
    }

    // ➕ Add a new note
    @PostMapping("/addnote")
    public ResponseEntity<Notes> addNote(@RequestBody Notes note) {
        return ResponseEntity.ok(notesService.addNote(note));
    }

    // ✏️ Update a note by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable String id, @RequestBody Notes updatedNote) {
        return ResponseEntity.ok(notesService.updateNote(id, updatedNote));
    }

    // ❌ Delete a note by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable String id) {
        notesService.deleteNote(id);
        return ResponseEntity.ok("Note deleted with id: " + id);
    }
}
