using System;
using System.Runtime.Serialization;

namespace Booking.com.controller.exceptions
{
    public class InvalidNewEntityException : Exception
    {
        public InvalidNewEntityException()
        {
        }

        public InvalidNewEntityException(string message) : base(message)
        {
        }

        public InvalidNewEntityException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected InvalidNewEntityException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
