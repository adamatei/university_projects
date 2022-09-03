using FollowService.Models;
using System.Collections.Generic;

namespace FollowService.DataAccess
{
    public class FakeRepository
    {
        public List<Follow> Follows { get; set; } = new List<Follow>() { new Follow { Id = 1, Followed = "Ada", Follower = "Ilya", isAccepted = true, isPending = false }, 
                                                                         new Follow { Id = 2, Followed = "Ilya", Follower = "Ada", isAccepted = false, isPending = false } };
    }
}
