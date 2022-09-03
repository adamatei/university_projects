using System.Collections.Generic;

namespace PostService.Models
{
    public class Tag
    {
        public virtual int Id { get; set; }
        public virtual string Content { get; set; }
        public virtual string Type { get; set; }  

    }
}
