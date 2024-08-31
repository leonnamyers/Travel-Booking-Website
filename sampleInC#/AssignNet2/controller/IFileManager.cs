using System.Collections.Generic;

namespace Booking.com.controller
{
    public interface IFileManager<T>
    {
        List<T> DeserializeEntitiesFromFile();
        
        void SerializeEntitiesToFile();

        void UpdateDetails(T t, Dictionary<string, string> modifiedProperties);

        void AddNewEntity(T t);
        void AddNewEntity(Dictionary<string, string> entityProperties);

        // this method deletes from both the in-memory Object list AND the text file
        void DeleteEntity(T t);
    }
}
