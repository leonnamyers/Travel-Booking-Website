using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Windows.Forms;

namespace Booking.com.controller
{
    using exceptions;
    using System.Linq;
    using validation;

    public class UserFileManager : IFileManager<User>
    {
        public static readonly string FilePath = Path.Combine(Directory.GetParent(Environment.CurrentDirectory).Parent.Parent.FullName, "user_details.txt");
        private List<User> users = new List<User>();

        public UserFileManager()
        {
            DeserializeEntitiesFromFile();
        }

        public List<User> DeserializeEntitiesFromFile()
        {
            string userData = File.ReadAllText(FilePath);

            JsonSerializerOptions options = new JsonSerializerOptions
            {
                WriteIndented = true,
                Converters = { new UserConverter() },
            };
            try
            {
                List<User> userList = JsonSerializer.Deserialize<List<User>>(userData, options);
                return userList;
            }
            catch (JsonException)
            {
                Console.WriteLine("List Empty");
            }
            return null;
        }

        public void SerializeEntitiesToFile()
        {
            JsonSerializerOptions options = new JsonSerializerOptions
            {
                WriteIndented = true,
                Converters = { new UserConverter() },
            };
            string writeUsers = JsonSerializer.Serialize(users, options);
            File.WriteAllText(FilePath, writeUsers);
        }

        public void AddNewEntity(User newUser)
        {
            users = DeserializeEntitiesFromFile();
            if (users.Any(user => user.Email == newUser.Email))
            {
                MessageBox.Show("There is already an existing user registered with that email. Please login or choose another email address", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                throw new DuplicateUserException();
            }
            users.Add(newUser);
            SerializeEntitiesToFile();
        }

        public void AddNewEntity(Dictionary<string, string> userProperties)
        {
            // the UserFormValidation already displays message boxes for the x input field that is causing issues, so no need to display again here.
            bool validUserDetailInputs = UserFormValidation.AreCustomerRegistrationInputsValid(userProperties);
            if (!validUserDetailInputs)
            {
                throw new InvalidInputException();
            }
            string address = $"{userProperties["StreetAddress"]} {userProperties["Suburb"]} {userProperties["State"]} {userProperties["Postcode"]}";
            User customer = new Customer(userProperties["Email"], userProperties["Password"], userProperties["FirstName"], userProperties["LastName"], userProperties["Phone"], address);
            AddNewEntity(customer);
        }

        // for changing account details of a customer e.g 'First Name' or 'Address'
        // NOT for updating bookings made by customers; that logic is in BookingManager.UpdateBooking(...)
        public void UpdateDetails(User userToUpdate, Dictionary<string, string> modifiedUserProperties)
        {
            users = DeserializeEntitiesFromFile();
            int index = users.FindIndex(user => user.Email == userToUpdate.Email);
            if (index == -1)
            {
                string errorMessage = "Error: Customer not found in file";
                MessageBox.Show(errorMessage);
                throw new InvalidUpdateException(errorMessage);
            }
            else
            {
                // its okay if the user leaves their new email as their original email (no change), but it is not okay if they try to change to an existing users email
                if (users.Any(user => user.Email != userToUpdate.Email && user.Email == modifiedUserProperties["Email"]))
                {
                    MessageBox.Show("There is already an existing user registered with that email. Please login or choose another email address", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    throw new DuplicateUserException();
                }
                users[index].Email = modifiedUserProperties["Email"];
                users[index].Password = modifiedUserProperties["Password"];
                users[index].FirstName = modifiedUserProperties["FirstName"];
                users[index].LastName = modifiedUserProperties["LastName"];
                users[index].Phone = modifiedUserProperties["Phone"];
                users[index].Address = modifiedUserProperties["Address"];
                SerializeEntitiesToFile();
            }
        }

        public User GetUserWithLoginDetails(string email, string password)
        {
            User user = null;
            if (UserFormValidation.AreLoginInputsValid(email, password))
            {
                user = SearchForUserWithCredentials(email, password);
            }
            return user;
        }
        private User SearchForUserWithCredentials(string email, string password)
        {
            List<User> userList = DeserializeEntitiesFromFile();
            if (userList != null)
            {
                foreach (User user in userList)
                {
                    if (user.Email == email)
                    {
                        if (user.Password == password)
                        {
                            return user;
                        }
                        else
                        {
                            return null;
                        }
                    }
                }
            }
            return null;
        }

        public void DeleteEntity(User userToDelete)
        {
            users = DeserializeEntitiesFromFile();
            try
            {
                int index = users.FindIndex(user => user.Email == userToDelete.Email);
                users.RemoveAt(index);
                SerializeEntitiesToFile();
                MessageBox.Show($"Success! The customer '{userToDelete.Email}' was deleted from the system");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }


    public class UserConverter : JsonConverter<User>
    {
        public override User Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            using (JsonDocument doc = JsonDocument.ParseValue(ref reader))
            {
                var root = doc.RootElement;
                if (root.TryGetProperty("Type", out var typeProp) && typeProp.GetInt32() == 1)
                {
                    return JsonSerializer.Deserialize<Customer>(root.GetRawText());
                }
                else if (root.TryGetProperty("Type", out typeProp) && typeProp.GetInt32() == 0)
                {
                    return JsonSerializer.Deserialize<Admin>(root.GetRawText());
                }
                else
                {
                    Console.WriteLine("Error with file");
                    Console.ReadKey();
                    return JsonSerializer.Deserialize<User>(root.GetRawText());
                }
            }
        }

        public override void Write(Utf8JsonWriter writer, User value, JsonSerializerOptions options)
        {
            writer.WriteStartObject();
            writer.WriteString("Email", value.Email);
            writer.WriteString("Password", value.Password);
            writer.WriteString("FirstName", value.FirstName);
            writer.WriteString("LastName", value.LastName);
            writer.WriteString("Phone", value.Phone);
            writer.WriteString("Address", value.Address);
            writer.WriteNumber("Type", (int)value.Type);

            /* Code before DataBase created - delete when ready
            if (value is Customer customer && customer.Bookings != null)
            {
                writer.WriteStartObject("Bookings");
                foreach (var booking in customer.Bookings)
                {
                    writer.WritePropertyName(booking.Key.ToString());
                    writer.WriteStringValue(booking.Value);
                }
                writer.WriteEndObject();
            }
            */
            writer.WriteEndObject();
        }
    }
}


