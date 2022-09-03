using System;

namespace NotificationService.Models
{
    public class Notification
    {
        public virtual int Id { get; set; }
        public virtual string Content { get; set; }
        public virtual DateTime CreatedOn { get; set; }
        public virtual string For { get; set; }
    }
}
