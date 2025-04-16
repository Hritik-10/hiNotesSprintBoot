package com.hritech.hinotes.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hritech.hinotes.Model.Notes;
import com.hritech.hinotes.Model.User;
import com.hritech.hinotes.Repository.MongoRepo;
import com.hritech.hinotes.Repository.UserRepo;

@Service
public class NotesService {

    @Autowired
    MongoRepo repo;
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    CurrentUser currentUser;
 
    public List<Notes> fetchAllNotes() {
        User user = currentUser.getCurrentUser();
        return repo.findByUser(user.getId());
    }

    public Notes addNote(Notes note) {
        User user = currentUser.getCurrentUser();
        note.setUser(user.getId());
        return repo.save(note);
    }
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
    // User user = userRepo.findByEmail(userDetail.getUsername())
    //     .orElseThrow(()->new RuntimeException("User not found"));
    // note.setUser(user.getId());
    // return repo.save(note);

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
