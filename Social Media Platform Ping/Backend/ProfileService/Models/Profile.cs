using System;

namespace ProfileService.Models
{
    public class Profile
    {
        public virtual int Id { get; set; }
        public virtual string Role { get; set; }
        public virtual string Username { get; set; }
        public virtual bool isPublic { get; set; } = true;
        public virtual bool isBlocked { get; set; } = false;   
        public virtual string Intro { get; set; } = "";        
        public virtual DateTime Birthday { get; set; } = new DateTime();
        public virtual string Job { get; set; } = "";
        public virtual string Education { get; set; } = "";
        public virtual string OriginCountry { get; set; } = "";
        public virtual string OriginCityy { get; set; } = "";
        public virtual string LivingCountry { get; set; } = "";
        public virtual string LivingCity { get; set; } = "";
    }
}
