using System;
using System.Collections.Generic;

namespace PostService.Models
{
    public class ApiPost
    {
        public int Id { get; set; }
        public string CreatedBy { get; set; }
        public DateTime CreatedOn { get; set; }
        public string Text { get; set; }
        public List<string> PostLikes { get; set; } = new List<string>();
        public List<string> HashTags { get; set; } = new List<string>();
        public List<string> Mentions { get; set; } = new List<string>();
    }
}
