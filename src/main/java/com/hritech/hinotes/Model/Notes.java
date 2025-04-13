package com.hritech.hinotes.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notes")
public class Notes {

    @Id
    private String id;
    private String user;
    private String title;
    private String description;
    private String tag;
   
}
