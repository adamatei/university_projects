using System;
using System.Collections.Generic;

namespace PostService.Models
{
    public class Post
    {
        public virtual int Id { get; set; }
        public virtual string CreatedBy { get; set; }
        public virtual DateTime CreatedOn { get; set; }
        public virtual string Text { get; set; }       
        public virtual ICollection<Tag> Tags { get; set; }
    }
}
