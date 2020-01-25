package com.example.artificialsoft;

public class ArtificalJsonTest
{
    //variables for all the data
    private String imageURL;
    private String id;
    private String user;
    private String name;
    private String designation;


    //constructor
    public ArtificalJsonTest(String imageURL, String id, String user, String name, String designation)
    {
        this.imageURL = imageURL;
        this.id = id;
        this.user = user;
        this.name = name;
        this.designation = designation;
    }


    //getter
    public String getImageURL() {
        return imageURL;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }
}
