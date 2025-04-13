package com.hritech.hinotes.Service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hritech.hinotes.Model.Notes;
import com.hritech.hinotes.Repository.MongoRepo;

@Service
public class NotesService {

    @Autowired
    MongoRepo repo;

    public List<Notes> fetchAllNotes() {
        return repo.findAll();
    }

    public Notes addNote(Notes note) {
        return repo.save(note);
    }

    public Notes updateNote(String id,Notes updatednote){
        Optional<Notes> existingNote = repo.findById(id);
        if(existingNote.isPresent()){
            Notes note = existingNote.get();
            if(updatednote.getTitle()!=null) note.setTitle(updatednote.getTitle());
            if(updatednote.getUser()!=null)note.setUser(updatednote.getUser());
            if(updatednote.getDescription()!=null)note.setDescription(updatednote.getDescription());
            if(updatednote.getTag()!=null)note.setTag(updatednote.getTag());
            return repo.save(note);
        }else{
            throw new RuntimeException("Note not found with id:"+id);
        }
    }

    public void deleteNote(String id){
        repo.deleteById(id);
    }

}
