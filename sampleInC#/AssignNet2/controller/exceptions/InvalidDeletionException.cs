using System;

namespace Booking.com.controller.exceptions
{
    public class InvalidDeletionException : Exception
    {
        public InvalidDeletionException()
        {
        }
        public InvalidDeletionException(string message) : base(message)
        {
        }
        public InvalidDeletionException(string message, Exception innerException) : base(message, innerException)
        {
        }
    }
}
