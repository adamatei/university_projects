using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MassTransitModels
{
    public class MentionNotification
    {
        public string Mentioner { get; set; }
        public string Mentioned { get; set; }
        public int PostId { get; set; }
        public string PostText { get; set; }
        public DateTime MentionedOn { get; set; }
    }
}
