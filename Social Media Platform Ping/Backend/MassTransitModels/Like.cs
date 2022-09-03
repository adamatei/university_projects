using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MassTransitModels
{
    public class Like
    {
        public int Total { get; set; }
        public string LikedUsername { get; set; }
        public DateTime LikedOn { get; set; }
        public int PostId { get; set; }
        public string PostText { get; set; }
    }
}
