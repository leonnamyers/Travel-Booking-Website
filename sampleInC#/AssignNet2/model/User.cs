using Booking.com.controller;
using Booking.com.model;
using System.Collections.Generic;

public abstract class User
{
    public string Email { get; set; }
    public string Password { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Phone { get; set; }
    public string Address { get; set; }
    public UserType Type { get; set; }

    public static readonly UserFileManager FileManager = new UserFileManager();

    public User(string email, string password, string firstName, string lastName, string phone, string address, UserType type)
    {
        Email = email;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        Address = address;
        Type = type;
    }

    public static List<User> GetAllUsers()
    {
        return FileManager.DeserializeEntitiesFromFile();
    }
}