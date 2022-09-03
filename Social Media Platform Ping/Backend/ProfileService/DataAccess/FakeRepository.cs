using ProfileService.Models;
using System.Collections.Generic;

namespace ProfileService.DataAccess
{
    public class FakeRepository
    {
        public FakeRepository()
        {
            Profiles = new List<Profile>();
            Profiles.Add( new Profile { Id = 5, Username = "Ada", isPublic= true});
        }
        public List<Profile> Profiles { get; }
    }
}
