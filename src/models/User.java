/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sajeed Ahmed Galib Arnob
 */
public class User {
    
    public String username;
    private String password;
    
    public User(String newUsername, String newPassword)
    {
        username = newUsername;
        password = newPassword;
    }
    
    public static User loadUser(String enteredUsername) throws FileNotFoundException, IOException
    {
        File text_file = new File("User.txt");
        
        if (!text_file.exists())
        {
            throw new FileNotFoundException("User.txt does not exist");
        }
        
        try
        {
            FileReader fr = new FileReader("User.txt");
            BufferedReader br = new BufferedReader(fr);

            String eachLine;

            while ((eachLine = br.readLine()) != null)
            {
                if (eachLine.startsWith(enteredUsername))
                {
                    String[] credentials = eachLine.trim().split(",");
                    User loaded_user = new User(credentials[0], credentials[1]);

                    br.close();
                    fr.close();
                    return loaded_user;
                }
            }

            br.close();
            fr.close();
        }
        
        catch (IOException e)
        {
            throw new IOException("Error reading from User.txt");
        }
        
        return null;
    }
    
    /**
     * 
     * @param enteredPassword the password entered by the user
     * @return true if the password matches the current user's password
     */
    public boolean checkPassword(String enteredPassword)
    {
        return (enteredPassword.equals(password));
    }
    
    public void save() throws IOException
    {
        File file = new File("User.txt");
        if (!file.exists())
        {
            file.createNewFile();
        }
        try
        {
            FileWriter fw = new FileWriter("User.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(this.username + ',' + this.password + '\n');

            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            throw new IOException("Error writing to User.txt");
        }
    }
    
    /**
     * 
     * @return (String) username and password
     */
    @Override
    public String toString()
    {
        return "User <username: " + this.username + ", password: " + this.password + ">";
    }
    
}
