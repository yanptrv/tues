package org.elsys.springbootexam;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

@RestController
public class NotesResource {

    ArrayList<PostNoteID> notes = new ArrayList<>();

    @PostMapping("/notes")
    public ResponseEntity createNote(@RequestBody Note note) {
        PostNoteID idNote = new PostNoteID(notes.size(), note.text());
        notes.add(idNote);

        return ResponseEntity.status(201).body(idNote.getId());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity getNote(@PathVariable String id, @RequestHeader Map<String, String> headers) {
        try {
            PostNoteID post = notes.get(Integer.parseInt(id));
            if (post == null) {
                throw new Exception("No such ID");
            }
            if (headers.containsKey("char-case")) {
                if (headers.containsValue("lowercase")) {
                    return ResponseEntity.status(200).body(new PostNoteID(notes.get(Integer.parseInt(id)).getId(), notes.get(Integer.parseInt(id)).getText().toLowerCase(Locale.ROOT)));
                } else if (headers.containsValue("uppercase")) {
                    return ResponseEntity.status(200).body(new PostNoteID(notes.get(Integer.parseInt(id)).getId(), notes.get(Integer.parseInt(id)).getText().toUpperCase(Locale.ROOT)));
                }
            }
            return ResponseEntity.status(200).body(notes.get(Integer.parseInt(id)));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }


    }

    @PutMapping("/notes/{id}")
    public ResponseEntity changeNote(@RequestBody Note note, @PathVariable String id) {
        try {
            PostNoteID post = notes.get(Integer.parseInt(id));
            if (post == null) {
                throw new Exception("No such ID");
            }
            String oldText = notes.get(Integer.parseInt(id)).getText();
            notes.get(Integer.parseInt(id)).setText(note.text());

            return ResponseEntity.status(200).header("previous", oldText).body(new PostNoteID(notes.get(Integer.parseInt(id)).getId(), notes.get(Integer.parseInt(id)).getText()));
        } catch (Exception e) {

            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity deleteNote(@PathVariable String id) {
        try {
            PostNoteID post = notes.get(Integer.parseInt(id));
            if (post == null) {
                throw new Exception("No such ID");
            }
            notes.set(Integer.parseInt(id), null);

            return ResponseEntity.status(204).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
